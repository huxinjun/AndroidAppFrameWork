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
	protected IInjectionPresenter deffaultSource() {
		return new ViewInjector();
	}

	


	@Override
	public void inject(View target, Object value) {
		mSource.inject(target,value);
	}
	
}
