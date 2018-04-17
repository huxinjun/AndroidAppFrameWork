package com.app.presenter;

import android.content.Context;

import java.io.File;

import com.app.presenter.impl.MD5Presenter;

/**
 * MD5
 * @author xinjun
 *
 */
public class IMD5PresenterBridge extends IPresenterBridge<IMD5Presenter> implements IMD5Presenter{


	@Override
	public String getMd5(String str) {
		return mSource.getMd5(str);
	}

	@Override
	public String getMd5(File file) {
		return mSource.getMd5(file);
	}

	@Override
	protected IMD5Presenter deffaultSource() {
		return new MD5Presenter();
	}



	

	
}
