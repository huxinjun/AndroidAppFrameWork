package com.app.presenter.impl.annotation.request;

import com.app.annotation.request.Parser;
import com.app.annotation.request.RequestBaseUrl;
import com.app.presenter.IRequestPresenter;
import com.app.presenter.impl.annotation.AnnotationPresenter;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;

/**
 * @RequestBaseUrl解释器
 * @author XINJUN
 *
 */
public class RequestBaseUrlInterpreter extends AnnotationPresenter {

	@Override
	public void interpreter(Annotation annotation,
			InterpreterCallBack callBack, Object... context) {
		//Ignore
	}

	@Override
	public void interpreter(AnnotatedElement target,
			InterpreterCallBack callBack, Object... context) {
		RequestBaseUrl annotation = getAnnotation(target, RequestBaseUrl.class);
		if(target.getClass()==Class.class)
			IRequestPresenter.GLOBLE.requestBaseUrl=annotation.value();
		else{
			IRequestPresenter.RequestInfo info= (IRequestPresenter.RequestInfo) context[0];
			info.mBaseUrl=annotation.value();
		}
	}
	
	

}
