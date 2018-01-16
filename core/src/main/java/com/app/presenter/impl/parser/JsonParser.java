package com.app.presenter.impl.parser;

import android.content.Context;

import com.app.presenter.IParserPresenter;
import com.easyjson.EasyJson;

import java.lang.ref.WeakReference;

public class JsonParser implements IParserPresenter {

	private Context mContext;

	@Override
	public void setContext(Context context) {
		mContext=context;
	}

	@Override
	public Context getContext() {
		return mContext;
	}

	@Override
	public Object parse(String data, Class<?> entityClass) {
		return EasyJson.getJavaBean(data,entityClass);
	}

}
