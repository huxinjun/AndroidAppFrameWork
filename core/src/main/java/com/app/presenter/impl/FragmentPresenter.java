package com.app.presenter.impl;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.app.presenter.IFragmentPresenter;
import com.app.test.ULog;

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
	public void changeFragment(FragmentInfo... fragments){
		ULog.out("切换fragment到："+fragments[0].clazz.getName());
		FragmentActivity activity = (FragmentActivity) getContext();
		FragmentManager fm =activity.getSupportFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		for(FragmentInfo info:fragments){
			try {
				ft.replace(info.viewID,info.clazz.newInstance());
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		ft.commitAllowingStateLoss();
	}

}
