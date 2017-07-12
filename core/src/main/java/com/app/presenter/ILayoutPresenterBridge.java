package com.app.presenter;

import android.view.View;

import com.app.presenter.impl.LayoutPresenter;
import com.app.presenter.impl.LayoutPresenter.LayoutCreater;

/**
 * 布局管理代理类
 * @author xinjun
 *
 */
public class ILayoutPresenterBridge extends IPresenterBridge<ILayoutPresenter> implements ILayoutPresenter{

	@Override
	protected ILayoutPresenter deffaultSource() {
		return new LayoutPresenter();
	}

	
	@Override
	public void setSource(ILayoutPresenter source) {
		this.mSource=source;
	}


	@Override
	public void inflate(Class<?> createrClass,InflateCallBack callBack) {
		mSource.inflate(createrClass,callBack);
	}


	@Override
	public View findView(LayoutCreater creater, int viewId) {
		return mSource.findView(creater, viewId);
	}


	
	
}
