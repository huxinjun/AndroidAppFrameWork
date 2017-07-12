package com.app.presenter;

import java.util.HashMap;
import java.util.Map;

import android.view.View;

/**
 * Activity业务类
 * 主要负责:
 * 1.启动一个新的Activity
 * 2.按照Activity类声明的注解绑定布局,实例化初始的Fragment,并切换到相应的视图
 * 3.
 * @author xinjun
 *
 */
public interface IInjectionPresenter extends IPresenter {

	/**
	 * 默认的注入器
	 */
	public static final Map<Class<? extends View>,Class<? extends IInjectionPresenter>> DEFAULT_INJECTOR=new HashMap<Class<? extends View>,Class<? extends IInjectionPresenter>>();
	
	
	
	public abstract void inject(View target,Object value);

}
