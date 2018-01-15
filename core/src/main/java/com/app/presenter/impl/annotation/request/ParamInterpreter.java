package com.app.presenter.impl.annotation.request;

import com.app.annotation.request.Param;
import com.app.annotation.request.Params;
import com.app.presenter.IRequestPresenter;
import com.app.presenter.impl.annotation.AnnotationPresenter;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;

/**
 * @Param解释器
 * @author XINJUN
 *
 */
public class ParamInterpreter extends AnnotationPresenter {

	@Override
	public void interpreter(Annotation annotation,
			InterpreterCallBack callBack, Object... context) {
		IRequestPresenter.RequestInfo info= (IRequestPresenter.RequestInfo) context[0];
		Param anno = (Param) annotation;
		switch (anno.type()){
			case FILE:
				info.mParamPool.putFileParam(anno.key(),anno.value());
				break;
			case VALUE:
				info.mParamPool.putParam(anno.key(),anno.value());
				break;
		}
	}

	@Override
	public void interpreter(AnnotatedElement target,
			InterpreterCallBack callBack, Object... context) {
	}


	

}
