package com.app.presenter;

import android.content.Context;

import com.app.presenter.impl.FragmentPresenter;

/**
 * 布局管理代理类
 * @author xinjun
 * @param <T>
 *
 */
public class IFragmentPresenterBridge extends IPresenterBridge<IFragmentPresenter> implements IFragmentPresenter{


	@Override
	public void setContext(Context context) {
		this.mSource.setContext(context);
	}

	@Override
	public Context getContext() {
		return this.mSource.getContext();
	}

	@Override
	protected IFragmentPresenter deffaultSource() {
		return new FragmentPresenter();
	}


	@Override
	public void changeFragment(FragmentInfo... fragments) {
		mSource.changeFragment(fragments);
	}

	@Override
	public void setSource(IFragmentPresenter source) {
		mSource=source;
	}



	
	
}
