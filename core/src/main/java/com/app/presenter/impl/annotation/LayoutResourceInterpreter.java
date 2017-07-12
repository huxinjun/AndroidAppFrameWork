package com.app.presenter.impl.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;

import android.view.View;

import com.app.annotation.LayoutResource;
import com.app.presenter.impl.LayoutPresenter.LayoutCreater;

public class LayoutResourceInterpreter extends AnnotationPresenter{

	@Override
	public void interpreter(AnnotatedElement target,InterpreterCallBack callBack,Object... context) {
		LayoutCreater creater=(LayoutCreater) context[0];
		LayoutResource layoutResourceAnno =getAnnotation(target, LayoutResource.class);
		//根据creater类声明上配置的@LayoutResource实例化出View
		creater.setContentView(View.inflate(getContext(), layoutResourceAnno.value(), null));
		creater.onViewCreated();
	}

	@Override
	public void interpreter(Annotation annotation,
			InterpreterCallBack callBack, Object... context) {
		// TODO Auto-generated method stub
		
	}
	
}