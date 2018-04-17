package com.app.presenter;

import android.content.Context;

import com.app.presenter.impl.request.HttpClientRequest;

/**
 * 布局管理代理类
 * @author xinjun
 *
 */
public class IRequestPresenterBridge extends IPresenterBridge<IRequestPresenter> implements IRequestPresenter{



	@Override
	public void request(String requestName,ParamPool paramPool,DataCallBack callBack) {
		mSource.request(requestName,paramPool,callBack);
	}

	@Override
	public Object requestSync(String requestName,ParamPool paramPool,DataCallBack callBack) {
		return mSource.requestSync(requestName,paramPool,callBack);
	}

	@Override
	public void addRequestStatusListenner(String requestName,
			RequestListener listener) {
		mSource.addRequestStatusListenner(requestName, listener);
	}

	@Override
	public void notifyRequestStatusListenner(String requestName, RequestStatus status, Object data) {
		mSource.notifyRequestStatusListenner(requestName,status,data);
	}

	@Override
	protected IRequestPresenter deffaultSource() {
		return new HttpClientRequest();
	}



	
}
