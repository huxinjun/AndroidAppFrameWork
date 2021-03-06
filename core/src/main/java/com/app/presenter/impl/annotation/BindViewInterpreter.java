package com.app.presenter.impl.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;

import android.view.View;

import com.app.ULog;
import com.app.annotation.creater.BindView;
import com.app.presenter.impl.layout.LayoutCreater;

public class BindViewInterpreter extends AnnotationPresenter{

	@Override
	public void interpreter(AnnotatedElement target,InterpreterCallBack callBack,Object... context) {
		LayoutCreater creater=(LayoutCreater) context[0];
		BindView bindViewAnno =getAnnotation(target, BindView.class);
		//这个注解可能会加在类上 或者字段上
		if(target instanceof Class) {
			//加在类上了
			creater.setContentView(View.inflate(getContext(),bindViewAnno.value(),null));
			if(callBack!=null)
				callBack.onCompleted(BindView.class);
		}else if(target instanceof Field) {
			//加在字段上了
			//根据creater类声明上配置的@LayoutResource实例化出View
			Field viewField= (Field) context[1];
			if(viewField==null)
				return;
			try {
				//反射注入控件实例
				viewField.set(creater,creater.getContentView().findViewById(bindViewAnno.value()));
			} catch (IllegalAccessException e) {
				throw new RuntimeException("申明的BinView字段类型和布局中的类型不匹配："+creater.getClass().getName()+"."+viewField.getName());
			}

		}


	}

	@Override
	public void interpreter(Annotation annotation,
			InterpreterCallBack callBack, Object... context) {
		// TODO Auto-generated method stub
		
	}


	
}