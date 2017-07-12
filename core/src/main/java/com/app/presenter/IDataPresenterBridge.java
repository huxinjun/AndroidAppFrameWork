package com.app.presenter;

import com.app.presenter.IRequestPresenter.ParamPool;
import com.app.presenter.impl.DataPresenter;

/**
 * 数据请求桥接类
 * @author xinjun
 *
 */
public class IDataPresenterBridge extends IPresenterBridge<IDataPresenter>
		implements IDataPresenter {

	@Override
	public void sendRequestDataCommand(RequestDataCommand command) {
		mSource.sendRequestDataCommand(command);
	}

	@Override
	protected IDataPresenter deffaultSource() {
		return new DataPresenter();
	}

	@Override
	public void setSource(IDataPresenter source) {
		mSource=source;
	}

	@Override
	public void request(String requestName, Option option, ParamPool paramPool) {
		mSource.request(requestName, option, paramPool);
	}

	@Override
	public void addNotifyHandler(String requestName,
			Class<?> attentionDataClass, DataChangedHandler handler) {
		mSource.addNotifyHandler(requestName, attentionDataClass, handler);
	}

}
