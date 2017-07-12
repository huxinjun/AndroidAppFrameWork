package com.app.presenter.impl.annotation.request;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;

import com.app.annotation.request.RequestUrlsPackage;
import com.app.presenter.IRequestPresenter;
import com.app.presenter.IRequestPresenterBridge;
import com.app.presenter.PresenterManager;
import com.app.presenter.impl.annotation.AnnotationPresenter;
/**
 * @RequestUrlsPackage解释器
 * @author XINJUN
 *
 */
public class RequestUrlsPackageInterpreter extends AnnotationPresenter {

	@Override
	public void interpreter(Annotation annotation,
			InterpreterCallBack callBack, Object... context) {
		//Ignore
	}

	@Override
	public void interpreter(AnnotatedElement target,
			InterpreterCallBack callBack, Object... context) {
		RequestUrlsPackage rPackage = getAnnotation(target, RequestUrlsPackage.class);
		IRequestPresenter.GLOBLE.urlPackage=rPackage.value();
	}
	
	public IRequestPresenter getRequester(){
		return PresenterManager.getInstance().findPresenter(IRequestPresenterBridge.class);
	}

}
