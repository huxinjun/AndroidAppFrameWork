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
	protected ILayoutPresenter deffaultSource() {
		return new LayoutPresenter();
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
