package com.app.presenter.impl.request;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Handler;

import com.app.presenter.IDataPresenter.RequestListener;
import com.app.presenter.impl.parser.JsonParser;
import com.app.presenter.IActivityPresenterBridge;
import com.app.presenter.IMD5Presenter;
import com.app.presenter.IMD5PresenterBridge;
import com.app.presenter.IParserPresenterBridge;
import com.app.presenter.IPersistentPresenter;
import com.app.presenter.IPersistentPresenterBridge;
import com.app.presenter.IRequestPresenter;
import com.app.presenter.PresenterManager;

public abstract class RequestPresenter implements IRequestPresenter {

	public abstract Bitmap getImage(RequestInfo requestInfo);
	public abstract File getFile(RequestInfo requestInfo);
	public abstract Object getData(RequestInfo requestInfo);


	private WeakReference<Context> mContext;

	@Override
	public void setContext(Context context) {
		mContext=new WeakReference<Context>(context);
	}

	@Override
	public Context getContext() {
		return mContext.get();
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
	public void request(RequestInfo requestInfo) {
		new CustomerAsyncTask(requestInfo).execute();
	}

	@Override
	public void addRequestStatusListenner(String requestName,RequestListener listener) {

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
			if(mInfo.mDialog!=null)
				mInfo.mDialog.show();
			mInfo.mHandler=new Handler();
		}
		
		@Override
		protected void onPostExecute(Object result) {
			super.onPostExecute(result);
			if(mInfo.mDialog!=null)
				mInfo.mDialog.dismiss();
		}
		
		@Override
		protected Object doInBackground(Void... params) {
			if(mInfo.mParser==null)
				getParser().setSource(new JsonParser());
			
			//检查是否需要使用磁盘缓存
			if(mInfo.isUseDiscCache){
				//获取本地缓存的数据
				mInfo.mDiscResult = getLocalCacheEntity(mInfo);
				if(mInfo.mDiscResult!=null)
					mInfo.mCallBack.onCacheComming(mInfo.mDiscResult);
			}
			
			//是否请求模板数据
			if(mInfo.isUseTempleteData){
				String data=getTempleteData(mInfo.mTempleteDataPackage, mInfo.mTempleteDataFileName);
				Object parse = getParser().parse(data, mInfo.mEntityType);
				if(mInfo.mDiscResult!=null)
					mInfo.mCallBack.onCacheComming(mInfo.mDiscResult);
			}
			Object result=null;
			if(mInfo.mResultType==ResultType.STRING){
				result=getData(mInfo);
				mInfo.mServerResult=result;
				//检查本地和网络数据是否相同
				if(mInfo.mServerResult!=null && !mInfo.mServerResult.equals(mInfo.mDiscResult))
					//不同时会返回最新的数据
					mInfo.mCallBack.onDataComming(result);
			}
			else if(mInfo.mResultType==ResultType.IMAGE){
				result=getImage(mInfo);
				mInfo.mCallBack.onDataComming(result);
			}
			else if(mInfo.mResultType==ResultType.FILE){
				result=getFile(mInfo);
				mInfo.mCallBack.onDataComming(result);
			}
			return null;
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
