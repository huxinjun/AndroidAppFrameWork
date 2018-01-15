package com.app.presenter.impl.annotation;

import android.view.View;
import android.widget.TextView;

import com.app.annotation.creater.BindView;
import com.app.annotation.request.BindUploadProgress;
import com.app.presenter.impl.layout.LayoutCreater;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;

public class BindUploadProgressInterpreter extends AnnotationPresenter{

	@Override
	public void interpreter(AnnotatedElement target,InterpreterCallBack callBack,Object... context) {
		LayoutCreater creater=(LayoutCreater) context[0];
		Field field= (Field) context[1];
		TextView targetView;
		try {
			View view= (View) field.get(creater);
			if(!(view instanceof TextView))
				throw new RuntimeException("BindUploadProgress注解只能绑定在TextView上！");
			else
				targetView= (TextView) view;
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
		BindUploadProgress annotation =getAnnotation(target, BindUploadProgress.class);
		//TODO 在此创建一个网络请求的监听器，并在监听到数据后实时修改界面

	}

	@Override
	public void interpreter(Annotation annotation,
			InterpreterCallBack callBack, Object... context) {

	}


	
}