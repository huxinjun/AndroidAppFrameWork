package com.app.presenter.impl;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;
import android.text.TextUtils;

import com.app.ULog;
import com.app.annotation.request.RequestUrl;
import com.app.presenter.IAnnotationPresenter;
import com.app.presenter.IAnnotationPresenterBridge;
import com.app.presenter.IDataPresenter;
import com.app.presenter.IEntityProxyPresenter;
import com.app.presenter.IEntityProxyPresenter.BeanProxyInfo;
import com.app.presenter.IEntityProxyPresenterBridge;
import com.app.presenter.IRequestPresenter;
import com.app.presenter.IRequestPresenter.DataCallBack;
import com.app.presenter.IRequestPresenterBridge;
import com.app.presenter.PresenterManager;
import com.app.presenter.IRequestPresenter.ParamPool;
import com.app.presenter.IRequestPresenter.RequestInfo;

/**
 * 数据处理器
 * 数据请求,分发
 * @author xinjun
 *
 */
public class DataPresenter implements IDataPresenter,Runnable {

	private WeakReference<Context> mContext;

	@Override
	public void setContext(Context context) {
		mContext=new WeakReference<Context>(context);
	}

	@Override
	public Context getContext() {
		return mContext.get();
	}

	public DataPresenter(){
		new Thread(this).start();
	}
	/**
	 * 内部数据请求指令集合
	 */
	private LinkedBlockingDeque<RequestDataCommand> mCommands=new LinkedBlockingDeque<IDataPresenter.RequestDataCommand>();
	/**
	 * 所有的数据
	 * Map<网络数据请求编号,请求的所有信息>
	 */
	private Map<String,RequestInfo> mDatas=new HashMap<String,RequestInfo>();


	@Override
	public void sendRequestDataCommand(RequestDataCommand command) {
		try {
			mCommands.putLast(command);
			ULog.out("DataPresenter.sendRequestDataCommand:"+command);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while(true){
			try {
				//从头部取出一个数据命令
				RequestDataCommand command = mCommands.takeFirst();
				//查询该命令的网络数据是否已经有了
				RequestInfo findInfo = mDatas.get(command.getRequestName());
				if(findInfo!=null){
					if(command.getType()==RequestDataCommand.TYPE_SINGLE_OBJECT){
						//如果有了的话就通知监听器
						command.getCallBack().onDataComming(command,findInfo.mServerResult);
						//TODO 在entry.value对象中查找命令需要的数据类型,完成后为其创建代理对象
					}else{
						//查找类型为List的字段
						ULog.out("11111111111111111111");
//						List listObj = findListObj(findInfo.mServerResult, "");
//						command.getCallBack().onDataComming(command,listObj);
					}
					continue;
				}
				//如果数据没有到来，那么将这个命令加到末尾继续等待网络数据的到来
				mCommands.offer(command);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

	private List findListObj(Object rootObj,String fields){
		if(rootObj==null)
			return null;
		Class<?> rootObjClass = rootObj.getClass();
		Field[] rootObjClassDeclaredFields = rootObjClass.getDeclaredFields();
		for(Field field:rootObjClassDeclaredFields){
			field.setAccessible(true);
			ULog.out("DataPresenter:"+field.getName()+":"+field.getType());
		}

		return null;
	}

















	@Override
	public void request(String requestName, Option option, ParamPool paramPool) {
		
		//请求网络，组装RequestInfo对象
		if(IRequestPresenter.GLOBLE.dataClass==null)
			throw new RuntimeException("请在Application类声明上配置@DatasDeclareClass注解，指明数据容器配置的类路径！");
		
		//解释数据配置类，上面可能配置了@RequestUrlsPackage注解
		getAnnotaionManager().interpreter(IRequestPresenter.GLOBLE.dataClass,null);


		//解释请求配置类，上面配置了@RequestBaseUrl,LocalJsonPackage注解
		getAnnotaionManager().interpreter(IRequestPresenter.GLOBLE.urlClass,null);

		if(IRequestPresenter.GLOBLE.urlClass==null)
			throw new RuntimeException("请在配置请求的类声明上配置@RequestUrlsPackage注解，指明请求URL所在的类路径！");

		//在数据配置类中查找到requestName相应的字段
		Field dataField = getFieldInClassByStaticFieldValue(IRequestPresenter.GLOBLE.dataClass, requestName);
		RequestUrl requestUrl = getAnnotaionManager().getAnnotation(dataField, RequestUrl.class);

		final RequestInfo mInfo=new RequestInfo();

		try {
			mInfo.mRequestName= (String) dataField.get(null);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		//在url请求配置类中查找到@RequestUrl对应的字段
		Field urlField = getFieldInClassByStaticFieldValue(IRequestPresenter.GLOBLE.urlClass, requestUrl.value());

		getAnnotaionManager().interpreter(urlField, null,mInfo);
		getAnnotaionManager().interpreter(dataField, null,mInfo);

		List<IRequestPresenter.Param> params = mInfo.mParamPool.getParams();
		boolean hasFileParam=false;
		for(IRequestPresenter.Param param:params)
			if(param.getType()== IRequestPresenter.ParamType.FILE){
				hasFileParam=true;
				break;
			}
		mInfo.mResultType= hasFileParam?IRequestPresenter.ResultType.FILE:IRequestPresenter.ResultType.STRING;
		mInfo.mCallBack=new DataCallBack() {
			
			@Override
			public void onCacheComming(Object object) {
				super.onCacheComming(object);
				mInfo.mDiscResult=object;

				//TODO 暂时不实现缓存
				//缓存的实体来了
				//1.如果是第一次请求该名称的数据，先创建代理对象
//				if(mInfo.mDataProxy==null)
//					mInfo.mDataProxy=getProxyManager().creatJavaProxy(mInfo.mEntityType, mInfo.mDiscResult);

				//2.如果已经有代理对象，检查数据是替换还是追加
				
				//3.如果是替换...
				
				//4.如果是追加...
			}
			
			@Override
			public void onDataComming(Object object) {
				mInfo.mServerResult=object;
				mDatas.put(mInfo.mRequestName,mInfo);
				//TODO 暂时不实现缓存
				//服务器最新的数据来了
				//1.检查是否有缓存数据，没有缓存时再查看是否有该类的代理，如果没有：创建代理，如果有：检查数据是替换还是追加
				
				//2.如果有缓存数据，那么肯定有代理对象，对比旧数据和新数据的区别，并调用代理相应的更新方法
			}
		};
		//RequetInfo创建完毕，去请求吧^_^
		getRequester().request(mInfo);
	}
	
	
	@Override
	public void addNotifyHandler(String requestName,Class<?> attentionDataClass, DataChangedHandler handler) {
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 根据字段值在一个class对象中找寻相应的字段
	 * @param clazz 目标class
	 * @param value 目标class中某个字段(静态)的值
	 * @return 值所对应的字段
	 */
	private Field getFieldInClassByStaticFieldValue(Class<?> clazz,String value){
		
		Field[] fields = clazz.getDeclaredFields();
		for(Field field:fields){
			try {
				field.setAccessible(true);
				Object fvalue = field.get(null);
				if(fvalue.equals(value))
					return field;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		throw new RuntimeException("没有在"+clazz.getName()+"中找到值为"+value+"的字段!");
	}

	/**
	 * 执行替换数据的操作
	 * @param proxyInfo 数据的代理描述类
	 * @param newData 新的数据
	 */
	private void dataReplacer(BeanProxyInfo proxyInfo,Object newData){
		
	}
	/**
	 * 执行数据追加的操作
	 * @param proxyInfo 数据的代理描述类
	 * @param newData 新的数据
	 */
	private void dataAppender(BeanProxyInfo proxyInfo,Object newData){
		
	}
	
	
	
	
	
	
	
	
	/**
	 * 根据set方法名称获取字段名称
	 * @param methodName 方法名
	 * @return 字段名
	 */
	public String getFieldNameByMethodName(String methodName){
		return methodName.substring(3,3).toLowerCase()+methodName.substring(4);
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

}
