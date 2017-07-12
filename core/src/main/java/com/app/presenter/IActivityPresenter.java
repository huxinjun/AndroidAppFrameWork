package com.app.presenter;

import android.content.Context;

import com.app.SmartActivity;

/**
 * Activity业务类
 * 主要负责:
 * 1.启动一个新的Activity
 * 2.按照Activity类声明的注解绑定布局,实例化初始的Fragment,并切换到相应的视图
 * 3.
 * @author xinjun
 *
 */
public interface IActivityPresenter extends IPresenter {

	
	public abstract Context getContext();
	/**
	 * IActivityPresenter管理着Context
	 * 但是自己又不能凭空创建一个Context,所以他的创建方式有别于其他的Presenter们
	 */
	public abstract void bindApplicationContext(Context context);
	
	public abstract IActivityPresenter startActivity(Class<? extends SmartActivity> clazz);
	
	public abstract IActivityPresenter putParam(String key,Object value);
	
	public abstract void go();

}
