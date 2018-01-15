package com.app.presenter.impl.annotation.request;

import com.app.annotation.request.EntityType;
import com.app.annotation.request.UseDiscCache;
import com.app.presenter.IRequestPresenter;
import com.app.presenter.impl.annotation.AnnotationPresenter;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;

/**
 * @EntityType解释器
 * @author XINJUN
 *
 */
public class EntityTypeInterpreter extends AnnotationPresenter {

	@Override
	public void interpreter(Annotation annotation,
			InterpreterCallBack callBack, Object... context) {
		//Ignore
	}

	@Override
	public void interpreter(AnnotatedElement target,
			InterpreterCallBack callBack, Object... context) {
		IRequestPresenter.RequestInfo info= (IRequestPresenter.RequestInfo) context[0];
		EntityType annotation = getAnnotation(target, EntityType.class);
		info.mEntityType=annotation.value();
	}
	
	

}
