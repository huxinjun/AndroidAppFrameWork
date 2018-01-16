package com.app.presenter;

import android.content.Context;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.ArrayList;

import com.app.ULog;
import com.app.annotation.BindFieldName;
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

	private Context mContext;

	@Override
	public void setContext(Context context) {
		this.mContext=context;
	}

	@Override
	public Context getContext() {
		return mContext;
	}

	@Override
	public <T extends Annotation> T getAnnotation(AnnotatedElement target,
			Class<T> annoType) {
		return mSource.getAnnotation(target, annoType);
	}

	private Annotation findAnnotation(Annotation[] annotations,Class annoClazz){
		for (Annotation anno:annotations) {
			if(anno.annotationType()==annoClazz)
				return anno;
		}
		return null;
	}

	@Override
	public void interpreter(AnnotatedElement target,InterpreterCallBack callBack,Object... context) {
		Annotation[] annotations = target.getAnnotations();
		if(annotations==null || annotations.length==0)
			return;
		//BindView优先于其他注解
		//BindFieldName优先于其他注解，次于BindView
		ArrayList<Annotation> annos=new ArrayList<>();
		Annotation bindViewAnno = findAnnotation(annotations, BindView.class);
		if(bindViewAnno!=null)
			annos.add(bindViewAnno);
		Annotation bindFieldNameAnno = findAnnotation(annotations, BindFieldName.class);
		if(bindFieldNameAnno!=null)
			annos.add(bindFieldNameAnno);

		for(int i=0;i<annotations.length;i++){
			if(!annos.contains(annotations[i]))
				annos.add(annotations[i]);
		}

		//解释其他注解
		for(Annotation anno:annos){
			if(anno==null)
				continue;
			Interpreter interpreter = getAnnotation(anno.annotationType(), Interpreter.class);
			if(interpreter==null)
				throw new RuntimeException("请为"+anno.getClass().getName()+"配置@Interpreter注解,指名改注解使用的解释器类型!");
			try {
				//给定的目标class上有什么注解就创建相应的注解解释器去解释
				setSource(interpreter.value().newInstance());
				mSource.setContext(mContext);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			mSource.interpreter(target, callBack, context);
		}
	}


	@Override
	public void interpreter(Annotation annotation,InterpreterCallBack callBack,Object... context) {
		Interpreter interpreter = getAnnotation(annotation.annotationType(), Interpreter.class);
		if(interpreter==null)
			throw new RuntimeException("请为"+annotation.getClass().getName()+"配置@Interpreter注解,指名改注解使用的解释器类型!");
		try {
			//给定的目标class上有什么注解就创建相应的注解解释器去解释
			setSource(interpreter.value().newInstance());
			mSource.setContext(mContext);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mSource.interpreter(annotation, callBack, context);
	}
	
	
}
