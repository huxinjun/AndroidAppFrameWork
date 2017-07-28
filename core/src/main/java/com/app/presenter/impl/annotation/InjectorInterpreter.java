package com.app.presenter.impl.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;

import com.app.annotation.Injector;
import com.app.presenter.IInjectionPresenter;
import com.app.presenter.impl.LayoutPresenter.LayoutCreater;

/**
 * @Injector注解解释器
 * @author xinjun
 *
 */
public class InjectorInterpreter extends AnnotationPresenter{

	@Override
	public void interpreter(AnnotatedElement target,InterpreterCallBack callBack,Object... context) {
		final LayoutCreater creater=(LayoutCreater) context[0];
		final int viewID=(Integer)context[1];
		if(target.getClass()==Field.class){
			
			final Injector injector =getAnnotation(target, Injector.class);
			Class<? extends IInjectionPresenter> value = injector.value();
			creater.getContentView().findViewById(viewID).setTag(LayoutCreater.TAG_INJECTOR_CLASS, value);
		}
	}

	@Override
	public void interpreter(Annotation annotation,InterpreterCallBack callBack, Object... context) {
		//Ignore
	}
	
}