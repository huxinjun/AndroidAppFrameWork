package com.app.presenter.impl.request;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Handler;
import android.text.TextUtils;

import com.app.presenter.IAnnotationPresenter;
import com.app.presenter.IAnnotationPresenterBridge;
import com.app.presenter.IEntityProxyPresenter;
import com.app.presenter.IEntityProxyPresenterBridge;
import com.app.presenter.IRequestPresenterBridge;
import com.app.presenter.impl.parser.JsonParser;
import com.app.presenter.IMD5Presenter;
import com.app.presenter.IMD5PresenterBridge;
import com.app.presenter.IParserPresenterBridge;
import com.app.presenter.IPersistentPresenter;
import com.app.presenter.IPersistentPresenterBridge;
import com.app.presenter.IRequestPresenter;
import com.app.presenter.PresenterManager;
import com.app.utils.ReflectUtils;

public abstract class RequestPresenter implements IRequestPresenter {

	public abstract Bitmap getImage(RequestInfo requestInfo);
	public abstract File getFile(RequestInfo requestInfo);
	public abstract Object getData(RequestInfo requestInfo);


	private Context mContext;

	private Handler mHandler=new Handler();

	@Override
	public void setContext(Context context) {
		mContext=context;
	}

	@Override
	public Context getContext() {
		return mContext;
	}
	
	/**
	 * 获取模板数据字符串
	 * @return
	 */
	public String getTempleteData(String _package,String filename) {
		StringBuffer sb = null;
		try {
			if (_package == null)
				throw new RuntimeException("model protocal package must be not null,please check your BaseActivity Settings!");
			_package = _package.replaceAll("\\.", "/");
			_package = _package.startsWith("/") ? _package.substring(1,
					_package.length()) : _package;
			_package = _package.endsWith("/") ? _package.substring(0,
					_package.length() - 1) : _package;
			InputStreamReader reader = new InputStreamReader(this.getClass()
					.getClassLoader()
					.getResourceAsStream(_package + "/" + filename), "utf-8");
			int length = 0;
			char[] chars = new char[8096];
			sb = new StringBuffer();

			while ((length = reader.read(chars)) != -1) {
				sb.append(new String(chars, 0, length));
			}
			reader.close();
		} catch (NullPointerException e) {
			e.printStackTrace();
			throw new RuntimeException("could't find " + filename + " in "
					+ _package + ",please check your BaseActivity Settings!");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}








	@Override
	public Object requestSync(RequestInfo requestInfo) {
		if(requestInfo==null)
			return null;
		beforeReq(requestInfo);
		Object result=duringReq(requestInfo);
		afterReq(requestInfo);
		return result;
	}
	
	
	@Override
	public void request(RequestInfo requestInfo) {
		if(requestInfo==null)
			return;
		new CustomerAsyncTask(requestInfo).execute();
	}

	@Override
	public void addRequestStatusListenner(String requestName,RequestListener listener) {
		RequestInfo requestInfo = mAllRequests.get(requestName);
		if(requestInfo==null) {
			listener.onStatusChanged(RequestStatus.NO_REQUEST, "没有" + requestName + "这个网络数据请求");
			return;
		}
		synchronized (requestInfo.mListeners){
		    requestInfo.mListeners.add(listener);
        }
		//检查有没有这个网络请求
	}

	@Override
	public void notifyRequestStatusListenner(String requestName,RequestStatus status,Object data) {
		RequestInfo requestInfo = mAllRequests.get(requestName);
		synchronized (requestInfo.mListeners){
			Iterator<RequestListener> requestListeners = requestInfo.mListeners.iterator();
			while (requestListeners.hasNext()){
				RequestListener next = requestListeners.next();
				next.onStatusChanged(status,data);
				requestListeners.remove();
			}
		}

	}


	private void beforeReq(RequestInfo mInfo){
		notifyRequestStatusListenner(mInfo.mRequestName,RequestStatus.INIT,null);
		if(mInfo.mDialog!=null)
			mInfo.mDialog.show();
	}
	private Object duringReq(RequestInfo mInfo){
		if(mInfo.mParser==null)
			getParser().setSource(new JsonParser());

		Object result=null;
		if(mInfo.mResultType==ResultType.STRING){

			//是否请求模板数据
			if(mInfo.isUseTempleteData){
				String data=getTempleteData(mInfo.mTempleteDataPackage, mInfo.mTempleteDataFileName);
				Object parse = getParser().parse(data, mInfo.mEntityType);
				if(mInfo.mDiscResult!=null && mInfo.mCallBack!=null)
					mHandler.post(new Runnable() {
						@Override
						public void run() {
							mInfo.mCallBack.onCacheComming(mInfo.mDiscResult);
						}
					});
			}else
				//检查是否需要使用磁盘缓存
				if(mInfo.isUseDiscCache && mInfo.mCallBack!=null){
					//获取本地缓存的数据
					mInfo.mDiscResult = getLocalCacheEntity(mInfo);
					if(mInfo.mDiscResult!=null)
						mHandler.post(new Runnable() {
							@Override
							public void run() {
								mInfo.mCallBack.onCacheComming(mInfo.mDiscResult);
							}
						});
				}

			result=getData(mInfo);
			mInfo.mServerResult=result;
			//检查本地和网络数据是否相同
			if(mInfo.mServerResult!=null && !mInfo.mServerResult.equals(mInfo.mDiscResult) && mInfo.mCallBack!=null)
				//不同时会返回最新的数据
			{
				Object finalResult2 = result;
				mHandler.post(new Runnable() {
					@Override
					public void run() {
						mInfo.mCallBack.onDataComming(finalResult2);
					}
				});
			}
		}
		else if(mInfo.mResultType==ResultType.IMAGE){
			result=getImage(mInfo);
			if(mInfo.mCallBack!=null) {
				Object finalResult1 = result;
				mHandler.post(new Runnable() {
					@Override
					public void run() {
						mInfo.mCallBack.onDataComming(finalResult1);
					}
				});
			}
		}
		else if(mInfo.mResultType==ResultType.FILE){
			result=getFile(mInfo);
			if(mInfo.mCallBack!=null) {
				Object finalResult = result;
				mHandler.post(new Runnable() {
					@Override
					public void run() {
						mInfo.mCallBack.onDataComming(finalResult);
					}
				});
			}
		}
		return result;
	}
	private void afterReq(RequestInfo mInfo){
		if(mInfo.mDialog!=null)
			mInfo.mDialog.dismiss();
		notifyRequestStatusListenner(mInfo.mRequestName,RequestStatus.COMPLETED,null);
	}

	
	/**
	 * 异步任务
	 * @author xinjun
	 *
	 */
	public class CustomerAsyncTask extends AsyncTask<Void,Void,Object>{
		
		private RequestInfo mInfo;
		
		public CustomerAsyncTask(RequestInfo info) {
			mInfo=info;
		}
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			mAllRequests.put(mInfo.mRequestName,mInfo);
			beforeReq(mInfo);
		}
		
		@Override
		protected void onPostExecute(Object result) {
			super.onPostExecute(result);
			afterReq(mInfo);
			mAllRequests.remove(mInfo.mRequestName);
		}
		
		@Override
		protected Object doInBackground(Void... params) {
			return duringReq(mInfo);
		}
	}
	
	/**
	 * 获取本地缓存的实体数据
	 * 实体数据是以MD5(请求名称+参数toString)为文件名存储的
	 * @param mInfo
	 * @return
	 */
	private Object getLocalCacheEntity(RequestInfo mInfo){
		String md5 = getMd5Manager().getMd5(mInfo.toString());
		return getPersistenter().getObject(md5, mInfo.mEntityType);
	}

	@Override
	public RequestInfo build(String requestName, Option option, ParamPool paramPool) {
		if(TextUtils.isEmpty(requestName))
			return null;

		if(option==null)
			option= Option.REPLACE;

		if(IRequestPresenter.GLOBLE.urlClass==null)
			throw new RuntimeException("请在配置请求的类声明上配置@RequestUrlsPackage注解，指明请求URL所在的类路径！");

		//解释请求配置类，上面配置了@RequestBaseUrl,LocalJsonPackage注解
		getAnnotaionManager().interpreter(IRequestPresenter.GLOBLE.urlClass,null);


		final RequestInfo mInfo=new RequestInfo();
		mInfo.mBaseUrl=IRequestPresenter.GLOBLE.requestBaseUrl;
		mInfo.mRequestName= requestName;

		//在url请求配置类中查找到@RequestUrl对应的字段
		Field urlField = ReflectUtils.getFieldInClassByStaticFieldValue(IRequestPresenter.GLOBLE.urlClass, requestName);

		getAnnotaionManager().interpreter(urlField, null,mInfo);

		mInfo.mRequestUrl= mInfo.mBaseUrl + mInfo.mRequestName;
		mInfo.mResultType=IRequestPresenter.ResultType.STRING;
		if(mInfo.mParamPool!=null){
			List<Param> params = mInfo.mParamPool.getParams();
			boolean hasFileParam=false;
			for(IRequestPresenter.Param param:params)
				if(param.getType()== IRequestPresenter.ParamType.FILE){
					hasFileParam=true;
					break;
				}
			mInfo.mResultType= hasFileParam?IRequestPresenter.ResultType.FILE:mInfo.mResultType;
		}else
			mInfo.mParamPool=ParamPool.obtain();

		//RequetInfo创建完毕，去请求吧^_^
		return mInfo;
	}
























	public IAnnotationPresenter getAnnotaionManager(){
		return PresenterManager.getInstance().findPresenter(getContext(),IAnnotationPresenterBridge.class);
	}
	public IRequestPresenter getRequester(){
		return PresenterManager.getInstance().findPresenter(getContext(),IRequestPresenterBridge.class);
	}
	public IEntityProxyPresenter getProxyManager(){
		return PresenterManager.getInstance().findPresenter(getContext(),IEntityProxyPresenterBridge.class);
	}


	public IPersistentPresenter getPersistenter() {
		return PresenterManager.getInstance().findPresenter(getContext(),IPersistentPresenterBridge.class);
		
	}
	public IMD5Presenter getMd5Manager() {
		return PresenterManager.getInstance().findPresenter(getContext(),IMD5PresenterBridge.class);

	}
	private IParserPresenterBridge getParser(){
		return PresenterManager.getInstance().findPresenter(getContext(), IParserPresenterBridge.class);
	}

}
