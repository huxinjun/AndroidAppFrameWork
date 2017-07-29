package com.app.presenter.impl;

import android.content.Context;

import com.app.presenter.IFragmentPresenter;

import java.lang.ref.WeakReference;

/**
 * fragment管理器
 * fragment创建和切换等等
 * @author xinjun
 *
 */
public class FragmentPresenter implements IFragmentPresenter {

	private WeakReference<Context> mContext;

	@Override
	public void setContext(Context context) {
		mContext=new WeakReference<Context>(context);
	}

	@Override
	public Context getContext() {
		return mContext.get();
	}


	@Override
	public void changeFragment(FragmentInfo... fragments) {
		// TODO Auto-generated method stub
		
	}

}
