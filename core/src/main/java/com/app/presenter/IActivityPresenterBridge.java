package com.app.presenter;

import android.content.Context;

import com.app.SmartActivity;
import com.app.presenter.impl.ActivityPresenter;

/**
 * 布局管理代理类
 * @author xinjun
 *
 */
public class IActivityPresenterBridge extends IPresenterBridge<IActivityPresenter> implements IActivityPresenter{

	@Override
	protected IActivityPresenter deffaultSource() {
		return new ActivityPresenter();
	}

	
	@Override
	public void setSource(IActivityPresenter source) {
		this.mSource=source;
	}

	@Override
	public IActivityPresenter startActivity(Class<? extends SmartActivity> clazz) {
		return mSource.startActivity(clazz);
	}

	@Override
	public IActivityPresenter putParam(String key, Object value) {
		return mSource.putParam(key, value);
	}

	@Override
	public void go() {
		mSource.go();
	}

	@Override
	public void bindApplicationContext(Context context) {
		mSource.bindApplicationContext(context);
	}


	@Override
	public Context getContext() {
		return mSource.getContext();
	}

	

	
}
