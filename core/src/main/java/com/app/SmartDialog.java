package com.app;

import com.app.presenter.ILayoutPresenterBridge;
import com.app.presenter.PresenterManager;
import com.app.presenter.ILayoutPresenter.InflateCallBack;
import com.app.presenter.impl.LayoutPresenter.LayoutCreater;

import android.app.Dialog;
import android.content.Context;

public abstract class SmartDialog extends Dialog {

	private LayoutCreater mLayoutCreater;
	private Context context;
	
	
	
	public SmartDialog(Context context) {
		super(context);
		this.context=context;
		init();
	}

	private void init() {
		//根据类声明中的注解创建相应的LayoutCreater
		PresenterManager.getInstance().findPresenter(ILayoutPresenterBridge.class).inflate(this.getClass(),new InflateCallBack() {
			
			@Override
			public void onCompleted(LayoutCreater instance) {
				mLayoutCreater=instance;
			}
		});
		setContentView(mLayoutCreater.getContentView());
	}
	
	
}
