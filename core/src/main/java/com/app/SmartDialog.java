package com.app;

import com.app.annotation.creater.BindLayoutCreater;
import com.app.presenter.IAnnotationPresenter;
import com.app.presenter.IAnnotationPresenterBridge;
import com.app.presenter.PresenterManager;
import com.app.presenter.impl.layout.LayoutCreater;

import android.app.Dialog;
import android.content.Context;

import java.lang.annotation.Annotation;

public abstract class SmartDialog extends Dialog {

	private LayoutCreater mLayoutCreater;
	private Context context;
	
	
	
	public SmartDialog(final Context context) {
		super(context);
		this.context=context;
		if(mLayoutCreater==null) {
			ULog.out(this);
			//根据类声明中的注解创建相应的LayoutCreater
			PresenterManager.getInstance().findPresenter(context, IAnnotationPresenterBridge.class).interpreter(this.getClass(), new IAnnotationPresenter.InterpreterCallBack() {
				@Override
				public void onCompleted(Class<? extends Annotation> anno, Object... results) {
					if (anno == BindLayoutCreater.class) {
						mLayoutCreater = (LayoutCreater) results[0];
						mLayoutCreater.setContext(context);
					}
				}
			});
		}
		setContentView(mLayoutCreater.getContentView());
	}

}
