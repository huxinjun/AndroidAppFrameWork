package com.app.presenter.impl.annotation.request;

import android.content.Context;

import com.app.SmartDialog;
import com.app.annotation.Description;
import com.app.annotation.request.Dialog;
import com.app.presenter.IRequestPresenter;
import com.app.presenter.impl.annotation.AnnotationPresenter;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Constructor;

/**
 * @Dialog解释器
 * @author XINJUN
 *
 */
public class DialogInterpreter extends AnnotationPresenter {

	@Override
	public void interpreter(Annotation annotation,
			InterpreterCallBack callBack, Object... context) {
		//Ignore
	}

	@Override
	public void interpreter(AnnotatedElement target,
			InterpreterCallBack callBack, Object... context) {
		IRequestPresenter.RequestInfo info= (IRequestPresenter.RequestInfo) context[0];
		Dialog annotation = getAnnotation(target, Dialog.class);
		try {
			Class<? extends SmartDialog> clazz = annotation.value();
			Constructor<? extends SmartDialog> constructor = clazz.getConstructor(Context.class);
			info.mDialog=constructor.newInstance(getContext());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
