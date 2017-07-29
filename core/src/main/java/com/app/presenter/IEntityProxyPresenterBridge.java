package com.app.presenter;

import android.content.Context;

/**
 * 实体代理桥接
 * @author xinjun
 *
 */
public class IEntityProxyPresenterBridge extends IPresenterBridge<IEntityProxyPresenter> implements IEntityProxyPresenter{

	@Override
	protected IEntityProxyPresenter deffaultSource() {
		return null;
	}

	@Override
	public void setSource(IEntityProxyPresenter source) {
		mSource=source;
	}

	@Override
	public void setContext(Context context) {
		this.mSource.setContext(context);
	}

	@Override
	public Context getContext() {
		return this.mSource.getContext();
	}

	@Override
	public BeanProxyInfo creatJavaProxy(Class<?> clazz, Object bean) {
		return mSource.creatJavaProxy(clazz, bean);
	}

	@Override
	public BeanProxyInfo findJavaBeanProxy(BeanProxyInfo root, Object proxy) {
		return mSource.findJavaBeanProxy(root, proxy);
	}


	

	
}
