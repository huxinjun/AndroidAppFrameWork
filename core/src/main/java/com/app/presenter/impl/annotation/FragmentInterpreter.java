package com.app.presenter.impl.annotation;

import android.view.View;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;

import com.app.annotation.fragment.Fragment;
import com.app.presenter.IFragmentPresenterBridge;
import com.app.presenter.PresenterManager;
import com.app.presenter.IFragmentPresenter.FragmentInfo;
import com.app.presenter.impl.layout.LayoutCreater;

public class FragmentInterpreter extends AnnotationPresenter{

	
	
	
	@Override
	public void interpreter(AnnotatedElement target,InterpreterCallBack callBack,Object... context) {
		if(target.getClass()==Field.class){
			@SuppressWarnings("unused")
			LayoutCreater creater=(LayoutCreater) context[0];
			int viewID=0;

			Field targetField= (Field) context[1];
			Fragment annotation = targetField.getAnnotation(Fragment.class);
			Class clazz=annotation.clazz();
			try {
				View view= (View) targetField.get(creater);
				viewID=view.getId();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}



			Fragment fragment =getAnnotation(target, Fragment.class);
			FragmentInfo fInfo=new FragmentInfo(viewID,fragment.clazz());
			//切换到此fragment
			PresenterManager.getInstance().findPresenter(getContext(),IFragmentPresenterBridge.class).changeFragment(fInfo);
		}
		
		
	}


	@Override
	public void interpreter(Annotation annotation,InterpreterCallBack callBack, Object... context) {
		Fragment fragment=(Fragment) annotation;
		FragmentInfo fInfo=new FragmentInfo(fragment.containerId(),fragment.clazz());
		callBack.onCompleted(annotation.getClass(),fInfo);
	}
	
}