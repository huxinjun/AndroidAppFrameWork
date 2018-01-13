package com.app.presenter.impl.annotation.request;

import com.app.annotation.request.RequestBaseUrl;
import com.app.annotation.request.RequestUrl;
import com.app.presenter.IRequestPresenter;
import com.app.presenter.impl.annotation.AnnotationPresenter;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;

/**
 * @RequestUrl解释器
 * @author XINJUN
 *
 */
public class RequestUrlInterpreter extends AnnotationPresenter {

	@Override
	public void interpreter(Annotation annotation,
			InterpreterCallBack callBack, Object... context) {
		//Ignore
	}

	@Override
	public void interpreter(AnnotatedElement target,
			InterpreterCallBack callBack, Object... context) {
		IRequestPresenter.RequestInfo info= (IRequestPresenter.RequestInfo) context[0];
		RequestUrl annotation = getAnnotation(target, RequestUrl.class);
		info.mUrlPattener=annotation.value();
	}
	
	

}
