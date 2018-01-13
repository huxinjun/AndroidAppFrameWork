package com.app.presenter.impl.annotation.request;

import com.app.annotation.request.Dialog;
import com.app.annotation.request.MappingLocalJson;
import com.app.presenter.IRequestPresenter;
import com.app.presenter.impl.annotation.AnnotationPresenter;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;

/**
 * @MappingLocalJson解释器
 * @author XINJUN
 *
 */
public class MappingLocalJsonInterpreter extends AnnotationPresenter {

	@Override
	public void interpreter(Annotation annotation,
			InterpreterCallBack callBack, Object... context) {
		//Ignore
	}

	@Override
	public void interpreter(AnnotatedElement target,
			InterpreterCallBack callBack, Object... context) {
		IRequestPresenter.RequestInfo info= (IRequestPresenter.RequestInfo) context[0];
		MappingLocalJson annotation = getAnnotation(target, MappingLocalJson.class);
		info.isUseTempleteData=annotation.useTempleteJson();
		info.mTempleteDataFileName=annotation.fileName();
	}
	
	

}
