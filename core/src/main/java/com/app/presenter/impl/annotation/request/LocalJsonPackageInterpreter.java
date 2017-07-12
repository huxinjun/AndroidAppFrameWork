package com.app.presenter.impl.annotation.request;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;

import com.app.annotation.request.LocalJsonPackage;
import com.app.presenter.IRequestPresenter;
import com.app.presenter.impl.annotation.AnnotationPresenter;
/**
 * @LocalJsonPackage解释器
 * @author XINJUN
 *
 */
public class LocalJsonPackageInterpreter extends AnnotationPresenter {

	@Override
	public void interpreter(Annotation annotation,
			InterpreterCallBack callBack, Object... context) {
		//Ignore
	}

	@Override
	public void interpreter(AnnotatedElement target,
			InterpreterCallBack callBack, Object... context) {
		LocalJsonPackage jsonPackage = getAnnotation(target, LocalJsonPackage.class);
		IRequestPresenter.GLOBLE.localJsonPackage=jsonPackage.value();
	}

}
