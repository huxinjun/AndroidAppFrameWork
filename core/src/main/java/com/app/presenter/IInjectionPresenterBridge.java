package com.app.presenter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.content.Context;
import android.view.View;

import com.app.presenter.impl.injector.ViewInjector;
import com.app.presenter.impl.layout.LayoutCreater;

/**
 * 布局管理代理类
 * @author xinjun
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
		return new ViewInjector();
	}

	
	
	@Override
	public void setSource(IInjectionPresenter source) {
		mSource=source;
	}


	@Override
	public void inject(View target, Object value) {
		mSource.inject(target,value);
	}
	
}
