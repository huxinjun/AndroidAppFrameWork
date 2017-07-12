package com.app.presenter.impl.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;

import com.app.annotation.action.IAction;
import com.app.annotation.fragment.ChangeFragment;
import com.app.annotation.fragment.Fragment;
import com.app.presenter.IAnnotationPresenterBridge;
import com.app.presenter.IFragmentPresenter.FragmentInfo;
import com.app.presenter.IFragmentPresenterBridge;
import com.app.presenter.PresenterManager;
import com.app.presenter.impl.LayoutPresenter.LayoutCreater;

public class ChangeFragmentInterpreter extends AnnotationPresenter{

	@Override
	public void interpreter(AnnotatedElement target,InterpreterCallBack callBack,Object... context) {
		final LayoutCreater creater=(LayoutCreater) context[0];
		final int viewID=(Integer)context[1];
		if(target.getClass()==Field.class){
			
			final ChangeFragment changeFragment =getAnnotation(target, ChangeFragment.class);
			Fragment fragment = changeFragment.fragment();
			PresenterManager.getInstance().findPresenter(IAnnotationPresenterBridge.class).interpreter(fragment, new InterpreterCallBack() {
				
				@Override
				public void onCompleted(Class<? extends Annotation> anno, Object... results) {
					final FragmentInfo fInfo=(FragmentInfo) results[0];
					Runnable annoRunnable=new Runnable() {
						
						@Override
						public void run() {
							//切换到此fragment
							PresenterManager.getInstance().findPresenter(IFragmentPresenterBridge.class).changeFragment(fInfo);
						}
					};
					Class<? extends IAction> actionClazz = changeFragment.action();
					if(actionClazz!=null){
						try {
							IAction action = (IAction) actionClazz.newInstance();
							action.onAction(creater, viewID, annoRunnable);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}else
						annoRunnable.run();
				}
			}, context);
		}
	}

	@Override
	public void interpreter(Annotation annotation,InterpreterCallBack callBack, Object... context) {
		//Ignore
	}
	
}