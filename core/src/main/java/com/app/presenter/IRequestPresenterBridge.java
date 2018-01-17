package com.app.presenter;

import android.content.Context;

import com.app.presenter.IDataPresenter.RequestListener;
import com.app.presenter.impl.request.HttpClientRequest;

/**
 * 布局管理代理类
 * @author xinjun
 *
 */
public class IRequestPresenterBridge extends IPresenterBridge<IRequestPresenter> implements IRequestPresenter{

	@Override
	public void setContext(Context context) {
		this.mSource.setContext(context);
	}

	@Override
	public Context getContext() {
		return this.mSource.getContext();
	}
	@Override
	public void request(RequestInfo requestInfo) {
		mSource.request(requestInfo);
	}

	@Override
	public Object requestSync(RequestInfo requestInfo) {
		return mSource.requestSync(requestInfo);
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

	@Override
	public void setSource(IRequestPresenter source) {
		mSource=source;
	}


	
}
