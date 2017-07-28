package com.app.presenter;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;

import com.app.annotation.Interpreter;
import com.app.annotation.creater.BindView;
import com.app.presenter.impl.annotation.AnnotationPresenter;

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

		//BindView优先于其他注解
		for(int i=0;i<annotations.length;i++){
			if(annotations[i] instanceof BindView) {
				if(i==0)
					break;
				//交换位置,使BindView第一个进行解释
				Annotation tempAnno=annotations[0];
				annotations[0]=annotations[i];
				annotations[i]=tempAnno;
				break;
			}
		}
		//解释其他注解
		for(Annotation anno:annotations){
			if(anno==null)
				continue;
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
