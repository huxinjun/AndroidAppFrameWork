package com.app.presenter.impl.annotation.request;

import com.app.annotation.request.AccessSettings;
import com.app.presenter.IRequestPresenter;
import com.app.presenter.impl.annotation.AnnotationPresenter;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;

/**
 * @AccessSetting解释器
 * @author XINJUN
 *
 */
public class AccessSettingInterpreter extends AnnotationPresenter {

	@Override
	public void interpreter(Annotation annotation,
			InterpreterCallBack callBack, Object... context) {
		//Ignore
	}

	@Override
	public void interpreter(AnnotatedElement target,
			InterpreterCallBack callBack, Object... context) {
		IRequestPresenter.RequestInfo info= (IRequestPresenter.RequestInfo) context[0];
		AccessSettings annotation = getAnnotation(target, AccessSettings.class);
		info.mRequestMethod=annotation.accessMethod();
		info.isPersistence=annotation.persistence();
		info.isShowDialog=annotation.showDialog();
	}
	
	

}
