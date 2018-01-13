package com.app.presenter.impl.annotation.request;

import com.app.annotation.Description;
import com.app.annotation.request.ConnectionSet;
import com.app.presenter.IRequestPresenter;
import com.app.presenter.impl.annotation.AnnotationPresenter;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;

/**
 * @Description解释器
 * @author XINJUN
 *
 */
public class DescriptionInterpreter extends AnnotationPresenter {

	@Override
	public void interpreter(Annotation annotation,
			InterpreterCallBack callBack, Object... context) {
		//Ignore
	}

	@Override
	public void interpreter(AnnotatedElement target,
			InterpreterCallBack callBack, Object... context) {
		IRequestPresenter.RequestInfo info= (IRequestPresenter.RequestInfo) context[0];
		Description annotation = getAnnotation(target, Description.class);
		info.mDescription=info.mDescription==null?annotation.value():info.mDescription.concat("("+annotation.value()+")");
	}
	
	

}
