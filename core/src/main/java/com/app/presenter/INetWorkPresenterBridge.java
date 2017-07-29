package com.app.presenter;

import android.content.Context;

import com.app.presenter.impl.NetWorkPresenter;

/**
 * 布局管理代理类
 * @author xinjun
 *
 */
public class INetWorkPresenterBridge extends IPresenterBridge<INetWorkPresenter> implements INetWorkPresenter{

	@Override
	public void setContext(Context context) {
		this.mSource.setContext(context);
	}

	@Override
	public Context getContext() {
		return this.mSource.getContext();
	}

	@Override
	public int checkNetWork() {
		return mSource.checkNetWork();
	}

	@Override
	public String getProxyIP() {
		return mSource.getProxyIP();
	}

	@Override
	public int getProxyPort() {
		return mSource.getProxyPort();
	}

	@Override
	protected INetWorkPresenter deffaultSource() {
		return new NetWorkPresenter();
	}

	@Override
	public void setSource(INetWorkPresenter source) {
		mSource=source;
	}


	

	
}
