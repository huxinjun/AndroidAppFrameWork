package com.app.presenter;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;

import com.app.annotation.Interpreter;
import com.app.annotation.LayoutDataType;
import com.app.annotation.LayoutResource;
import com.app.annotation.creater.BindLayoutCreater;
import com.app.annotation.fragment.ChangeFragment;
import com.app.annotation.fragment.ChangeFragments;
import com.app.annotation.fragment.Fragment;
import com.app.presenter.impl.annotation.AnnotationPresenter;
import com.app.presenter.impl.annotation.BindLayoutCreaterInterpreter;
import com.app.presenter.impl.annotation.ChangeFragmentsInterpreter;
import com.app.presenter.impl.annotation.FragmentInterpreter;
import com.app.presenter.impl.annotation.ChangeFragmentInterpreter;
import com.app.presenter.impl.annotation.LayoutDataTypeInterpreter;
import com.app.presenter.impl.annotation.LayoutResourceInterpreter;

/**
 * 布局管理代理类
 * @author xinjun
 *
 */
public class IAnnotationPresenterBridge extends IPresenterBridge<IAnnotationPresenter> implements IAnnotationPresenter{


	@Override
	protected IAnnotationPresenter deffaultSource() {
		return new AnnotationPresenter() {
			
			@Override
			public void interpreter(AnnotatedElement target, InterpreterCallBack callBack,
					Object... context) {
				//ignore
			}

			@Override
			public void interpreter(Annotation annotation,
					InterpreterCallBack callBack, Object... context) {
				//ignore
			}
		};
	}

	@Override
	public void setSource(IAnnotationPresenter source) {
		mSource=source;
	}

	@Override
	public <T extends Annotation> T getAnnotation(AnnotatedElement target,
			Class<T> annoType) {
		return mSource.getAnnotation(target, annoType);
	}

	@Override
	public void interpreter(AnnotatedElement target,InterpreterCallBack callBack,Object... context) {
		Annotation[] annotations = target.getAnnotations();
		if(annotations==null || annotations.length==0)
			return;
		
		for(Annotation anno:annotations){
			Interpreter interpreter = getAnnotation(anno.getClass(), Interpreter.class);
			if(interpreter==null)
				throw new RuntimeException("请为"+anno.getClass().getName()+"配置@Interpreter注解,指名改注解使用的解释器类型!");
			try {
				//给定的目标class上有什么注解就创建相应的注解解释器去解释
				setSource(interpreter.value().newInstance());
			} catch (Exception e) {
				e.printStackTrace();
			}
			mSource.interpreter(target, callBack, context);
		}
	}


	@Override
	public void interpreter(Annotation annotation,InterpreterCallBack callBack,Object... context) {
		Interpreter interpreter = getAnnotation(annotation.getClass(), Interpreter.class);
		if(interpreter==null)
			throw new RuntimeException("请为"+annotation.getClass().getName()+"配置@Interpreter注解,指名改注解使用的解释器类型!");
		try {
			//给定的目标class上有什么注解就创建相应的注解解释器去解释
			setSource(interpreter.value().newInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}
		mSource.interpreter(annotation, callBack, context);
	}
	
	
}
