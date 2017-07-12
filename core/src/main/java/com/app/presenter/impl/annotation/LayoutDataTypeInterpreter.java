package com.app.presenter.impl.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;

import com.app.annotation.LayoutDataType;
import com.app.presenter.impl.LayoutPresenter.LayoutCreater;

public class LayoutDataTypeInterpreter extends AnnotationPresenter{

	@Override
	public void interpreter(AnnotatedElement target,InterpreterCallBack callBack, Object... context) {
		LayoutCreater creater=(LayoutCreater) context[0];
		//根据creater类声明上配置的@LayoutDataType设置此布局使用的数据类型
		LayoutDataType layoutDataTypeAnno =getAnnotation(creater.getClass(),LayoutDataType.class);
		creater.setContentDataType(layoutDataTypeAnno.value());
	}

	@Override
	public void interpreter(Annotation annotation,
			InterpreterCallBack callBack, Object... context) {
		// TODO Auto-generated method stub
		
	}
	
}