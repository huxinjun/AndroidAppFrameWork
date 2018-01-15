package com.app.presenter.impl.annotation;

import android.view.View;
import android.widget.TextView;

import com.app.annotation.BindFieldName;
import com.app.annotation.request.UseDiscCache;
import com.app.presenter.IRequestPresenter;
import com.app.presenter.impl.annotation.AnnotationPresenter;
import com.app.presenter.impl.injector.TextViewInjector;
import com.app.presenter.impl.layout.LayoutCreater;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;

/**
 * @UseDiscCache解释器
 * @author XINJUN
 *
 */
public class BindFieldNameInterpreter extends AnnotationPresenter {

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
		BindFieldName annotation = getAnnotation(target, BindFieldName.class);

		try {
			View view= (View) field.get(creater);
			view.setTag(LayoutCreater.TAG_INJECTOR_FIELD, annotation.value());
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}
	
	

}
