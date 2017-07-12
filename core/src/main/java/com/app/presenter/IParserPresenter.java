package com.app.presenter;

/**
 * 网络请求
 * @author xinjun
 *
 */
public interface IParserPresenter extends IPresenter {

	
	public Object parse(String data,Class<?> entityClass);
}
