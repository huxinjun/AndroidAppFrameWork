package com.app.presenter.impl.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;

import com.app.annotation.fragment.Fragment;
import com.app.presenter.IFragmentPresenterBridge;
import com.app.presenter.PresenterManager;
import com.app.presenter.IFragmentPresenter.FragmentInfo;
import com.app.presenter.impl.LayoutPresenter.LayoutCreater;

public class FragmentInterpreter extends AnnotationPresenter{

	
	
	
	@Override
	public void interpreter(AnnotatedElement target,InterpreterCallBack callBack,Object... context) {
		if(target.getClass()==Field.class){
			@SuppressWarnings("unused")
			LayoutCreater creater=(LayoutCreater) context[0];
			int viewID=(Integer)context[1];
			
			Fragment fragment =getAnnotation(target, Fragment.class);
			FragmentInfo fInfo=new FragmentInfo(viewID,fragment.clazz());
			//切换到此fragment
			PresenterManager.getInstance().findPresenter(IFragmentPresenterBridge.class).changeFragment(fInfo);
		}
		
		
	}


	@Override
	public void interpreter(Annotation annotation,InterpreterCallBack callBack, Object... context) {
		Fragment fragment=(Fragment) annotation;
		FragmentInfo fInfo=new FragmentInfo(fragment.containerId(),fragment.clazz());
		callBack.onCompleted(annotation.getClass(),fInfo);
	}
	
}