package com.app.presenter.impl.annotation;

import android.view.View;

import com.app.annotation.BindFieldName;
import com.app.annotation.BindMultiData;
import com.app.presenter.impl.layout.LayoutCreater;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;

/**
 * @BindMultiData解释器
 * @author XINJUN
 *
 */
public class BindMultiDataInterpreter extends AnnotationPresenter {

	@Override
	public void interpreter(Annotation annotation,
			InterpreterCallBack callBack, Object... context) {
		//Ignore
	}

	@Override
	public void interpreter(AnnotatedElement target,
			InterpreterCallBack callBack, Object... context) {
		LayoutCreater creater=(LayoutCreater) context[0];
		Field field= (Field) context[1];
		BindMultiData annotation = getAnnotation(target, BindMultiData.class);

		try {
			View view= (View) field.get(creater);
			view.setTag(LayoutCreater.TAG_MULTI_DATA_COUNT, annotation.value());
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}
	
	

}
