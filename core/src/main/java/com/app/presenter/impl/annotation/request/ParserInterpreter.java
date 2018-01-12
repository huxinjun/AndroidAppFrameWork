package com.app.presenter.impl.annotation.request;

import com.app.presenter.impl.annotation.AnnotationPresenter;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;

/**
 * @Parser解释器
 * @author XINJUN
 *
 */
public class ParserInterpreter extends AnnotationPresenter {

	@Override
	public void interpreter(Annotation annotation,
			InterpreterCallBack callBack, Object... context) {
		//Ignore
	}

	@Override
	public void interpreter(AnnotatedElement target,
			InterpreterCallBack callBack, Object... context) {
		//Ignore
	}
	
	

}
