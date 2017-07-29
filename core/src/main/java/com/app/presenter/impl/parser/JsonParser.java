package com.app.presenter.impl.parser;

import android.content.Context;

import com.app.presenter.IParserPresenter;

import java.lang.ref.WeakReference;

public class JsonParser implements IParserPresenter {

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
	public Object parse(String data, Class<?> entityClass) {
		return null;
	}

}
