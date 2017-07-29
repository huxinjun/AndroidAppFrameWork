package com.app.presenter;

import android.content.Context;
import android.view.View;

import com.app.presenter.impl.layout.LayoutCreater;
import com.app.presenter.impl.layout.LayoutPresenter;

/**
 * 布局管理代理类
 * @author xinjun
 *
 */
public class ILayoutPresenterBridge extends IPresenterBridge<ILayoutPresenter> implements ILayoutPresenter{

	@Override
	public void setContext(Context context) {
		this.mSource.setContext(context);
	}

	@Override
	public Context getContext() {
		return this.mSource.getContext();
	}

	@Override
	protected ILayoutPresenter deffaultSource() {
		return new LayoutPresenter();
	}

	
	@Override
	public void setSource(ILayoutPresenter source) {
		this.mSource=source;
	}


	@Override
	public void inflate(Class<? extends LayoutCreater> createrClass,InflateCallBack callBack) {
		mSource.inflate(createrClass,callBack);
	}


	@Override
	public View findView(LayoutCreater creater, int viewId) {
		return mSource.findView(creater, viewId);
	}


	
	
}
