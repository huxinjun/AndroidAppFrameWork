package com.app.presenter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.content.Context;
import android.view.View;

import com.app.presenter.impl.layout.LayoutCreater;

/**
 * 布局管理代理类
 * @author xinjun
 * @param <T>
 *
 */
public class IInjectionPresenterBridge extends IPresenterBridge<IInjectionPresenter> implements IInjectionPresenter{

	@Override
	public void setContext(Context context) {
		this.mSource.setContext(context);
	}

	@Override
	public Context getContext() {
		return this.mSource.getContext();
	}

	@Override
	protected IInjectionPresenter deffaultSource() {
		return null;
	}

	/**
	 * 获取默认的注入器(在DEFAULT_INJECTOR中配置了默认注入器的Class)
	 * @param target 目标视图的类型
	 * @return 默认的注入器
	 */
	private IInjectionPresenter deffaultSource(View target) throws Exception {
		
		Class<? extends IInjectionPresenter> resultClass=null;
		
		List<String> viewSuperClasses=new ArrayList<String>();
		Class<?> clazz=null;
		while((clazz=target.getClass()) !=Object.class){
			viewSuperClasses.add(clazz.getName());
			clazz=clazz.getSuperclass();
		}
		
		for(String clazzName:viewSuperClasses){
			Iterator<Class<? extends View>> iterator = DEFAULT_INJECTOR.keySet().iterator();
			while(iterator.hasNext()){
				Class<? extends View> key=iterator.next();
				if(clazzName.equals(key.getName())){
					resultClass=DEFAULT_INJECTOR.get(key);
					break;
				}
			}
			if(resultClass !=null)
				break;
		}
		
		return resultClass.newInstance();
	}
	
	
	@Override
	public void setSource(IInjectionPresenter source) {
		mSource=source;
	}


	@SuppressWarnings("unchecked")
	@Override
	public void inject(View target, Object value) {
		//配置了@Injector注解后view的tag下会记录其使用的注入器类型
		Class<IInjectionPresenter> injectorClass=(Class<IInjectionPresenter>) target.getTag(LayoutCreater.TAG_INJECTOR_CLASS);
		
		try {
			//木有配置Injector注解
			if(injectorClass==null){
				//使用默认的数据注入器
				IInjectionPresenter defaultInjector=deffaultSource(target);
				setSource(defaultInjector);
			}else
				setSource(injectorClass.newInstance());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		mSource.inject(target, value);
	}
	
}
