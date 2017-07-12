package com.app.presenter.impl.annotation.request;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;

import com.app.annotation.request.DatasPackage;
import com.app.presenter.IRequestPresenter;
import com.app.presenter.impl.annotation.AnnotationPresenter;
/**
 * @DatasPackage解释器
 * @author XINJUN
 *
 */
public class DatasPackageInterpreter extends AnnotationPresenter {

	@Override
	public void interpreter(Annotation annotation,
			InterpreterCallBack callBack, Object... context) {
		//Ignore
	}

	@Override
	public void interpreter(AnnotatedElement target,
			InterpreterCallBack callBack, Object... context) {
		DatasPackage rPackage = getAnnotation(target, DatasPackage.class);
		IRequestPresenter.GLOBLE.datasPackage=rPackage.value();
	}
	
	

}
