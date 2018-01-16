package com.app.presenter;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.os.Handler;


/**
 * 业务类的管理器,负责创建,销毁业务类
 * @author xinjun
 *
 */
public class PresenterManager {
	
	private static ThreadLocal<PresenterManager> mInstance;

	public Handler getHandler() {
		return mHandler;
	}

	private static Handler mHandler=new Handler();
	
	private Context context;
	//所有的业务类,按名称注册到此map中
	private Map<Class<IPresenterBridge<?>>,IPresenterBridge<?>> mPresenters=new HashMap<Class<IPresenterBridge<?>>,IPresenterBridge<?>>();
	
	private PresenterManager() {

	}
	public static PresenterManager getInstance(){
		if(mInstance==null){
			mInstance=new ThreadLocal<PresenterManager>();
			mInstance.set(new PresenterManager());
		}
		if(mInstance.get()==null)
			mInstance.set(new PresenterManager());
		return mInstance.get();
	}

	/**
	 * 获取一个业务代理类
	 * @param clazz 业务代理类的class
	 * @return 业务代理类
	 */
	@SuppressWarnings("unchecked")
	public <T extends IPresenterBridge<?>> T findPresenter(Context context,Class<T> clazz){
		if(context==null)
			throw new RuntimeException("PresenterManager.findPresenter第一个参数context不能为null");
		if(mPresenters.containsKey(clazz)) {
			T instance=(T) mPresenters.get(clazz);
			instance.setContext(context);
			return instance;
		}
		IPresenterBridge<T> presenterImpl = null;
		try {
			presenterImpl = (IPresenterBridge<T>) clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//设置默认的业务处理器
		presenterImpl.setSource(presenterImpl.deffaultSource());
		//将业务代理类存放起来
		mPresenters.put((Class<IPresenterBridge<?>>) clazz, presenterImpl);
		return findPresenter(context,clazz);
	}
	
	/**
	 * 销毁一个业务代理类
	 * @param clazz
	 */
	public void destoryPresenter(Class<? extends IPresenterBridge<?>> clazz){
		if(mPresenters.containsKey(clazz)){
			mPresenters.remove(clazz);
		}
	}
	
}
