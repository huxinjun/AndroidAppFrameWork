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
	protected IFragmentPresenter deffaultSource() {
		return new FragmentPresenter();
	}


	@Override
	public void changeFragment(FragmentInfo... fragments) {
		mSource.changeFragment(fragments);
	}




	
	
}
