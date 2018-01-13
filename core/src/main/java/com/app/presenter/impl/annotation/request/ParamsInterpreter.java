package com.app.presenter.impl.annotation.request;

import com.app.annotation.request.MappingLocalJson;
import com.app.annotation.request.Param;
import com.app.annotation.request.Params;
import com.app.presenter.IRequestPresenter;
import com.app.presenter.impl.annotation.AnnotationPresenter;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;

/**
 * @Params解释器
 * @author XINJUN
 *
 */
public class ParamsInterpreter extends AnnotationPresenter {

	@Override
	public void interpreter(Annotation annotation,
			InterpreterCallBack callBack, Object... context) {
		//Ignore
	}

	@Override
	public void interpreter(AnnotatedElement target,
			InterpreterCallBack callBack, Object... context) {
		IRequestPresenter.RequestInfo info= (IRequestPresenter.RequestInfo) context[0];
		if(info.mParamPool==null)
			info.mParamPool= IRequestPresenter.ParamPool.obtain();
		Params annotation = getAnnotation(target, Params.class);
		for (Param param : annotation.value()) {
			getAnnotationPresenter().interpreter(param,null,info);
		}
	}
	
	

}
