package com.app.presenter;

import android.content.Context;

import com.app.presenter.impl.parser.JsonParser;

/**
 * 布局管理代理类
 * @author xinjun
 *
 */
public class IParserPresenterBridge extends IPresenterBridge<IParserPresenter> implements IParserPresenter{

	@Override
	public void setContext(Context context) {
		this.mSource.setContext(context);
	}

	@Override
	public Context getContext() {
		return this.mSource.getContext();
	}

	@Override
	public Object parse(String data, Class<?> entityClass) {
		return mSource.parse(data, entityClass);
	}

	@Override
	protected IParserPresenter deffaultSource() {
		return new JsonParser();
	}

	@Override
	public void setSource(IParserPresenter source) {
		mSource=source;
	}



	
}
