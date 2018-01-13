package com.app.presenter.impl.annotation.request;

import com.app.annotation.request.ConnectionSet;
import com.app.presenter.IRequestPresenter;
import com.app.presenter.impl.annotation.AnnotationPresenter;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;

/**
 * @ConnectionSet解释器
 * @author XINJUN
 *
 */
public class ConnectionSetInterpreter extends AnnotationPresenter {

	@Override
	public void interpreter(Annotation annotation,
			InterpreterCallBack callBack, Object... context) {
		//Ignore
	}

	@Override
	public void interpreter(AnnotatedElement target,
			InterpreterCallBack callBack, Object... context) {
		IRequestPresenter.RequestInfo info= (IRequestPresenter.RequestInfo) context[0];
		ConnectionSet annotation = getAnnotation(target, ConnectionSet.class);
		info.mConnectionTimeOut=annotation.connectionTimeOut();
		info.mServerTimeOut=annotation.serverTimeOut();
		info.mRetryCount=annotation.retryCount();
	}
	
	

}
