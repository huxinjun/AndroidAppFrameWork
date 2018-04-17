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
	public Object parse(String data, Class<?> entityClass) {
		return mSource.parse(data, entityClass);
	}

	@Override
	protected IParserPresenter deffaultSource() {
		return new JsonParser();
	}




	
}
