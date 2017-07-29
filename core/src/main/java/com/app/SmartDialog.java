package com.app;

import com.app.presenter.impl.layout.LayoutCreater;

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
		setContentView(mLayoutCreater.getContentView());
	}
	
	
}
