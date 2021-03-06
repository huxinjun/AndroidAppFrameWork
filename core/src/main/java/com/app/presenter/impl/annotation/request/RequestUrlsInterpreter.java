package com.app.presenter.impl.annotation.request;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;

import com.app.annotation.request.RequestUrls;
import com.app.presenter.IRequestPresenter;
import com.app.presenter.impl.annotation.AnnotationPresenter;
/**
 * @RequestUrlsPackage解释器
 * @author XINJUN
 *
 */
public class RequestUrlsInterpreter extends AnnotationPresenter {

	@Override
	public void interpreter(Annotation annotation,
			InterpreterCallBack callBack, Object... context) {
		//Ignore
	}

	@Override
	public void interpreter(AnnotatedElement target,
			InterpreterCallBack callBack, Object... context) {
		RequestUrls rPackage = getAnnotation(target, RequestUrls.class);
		IRequestPresenter.GLOBLE.urlClass =rPackage.value();
	}

}
