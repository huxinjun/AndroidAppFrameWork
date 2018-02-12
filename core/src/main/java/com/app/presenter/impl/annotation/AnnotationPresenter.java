package com.app.presenter.impl.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;

import android.content.Context;

import com.app.presenter.IAnnotationPresenter;
import com.app.presenter.IAnnotationPresenterBridge;
import com.app.presenter.ILayoutPresenter;
import com.app.presenter.ILayoutPresenterBridge;
import com.app.presenter.IRequestPresenter;
import com.app.presenter.IRequestPresenterBridge;
import com.app.presenter.PresenterManager;

/**
 * 关于注解的逻辑类
 * @author xinjun
 *
 */
public abstract class AnnotationPresenter implements IAnnotationPresenter{

	private Context mContext;

	@Override
	public void setContext(Context context) {
		mContext=context;
	}

	@Override
	public Context getContext() {
		return mContext;
	}


	/**
	 * 具体此方法的实现逻辑由众多解释器来做,这样也便于以后支持新的注解
	 */
	@Override
	public abstract void interpreter(AnnotatedElement target,InterpreterCallBack callBack,Object... context);

	@Override
	public <T extends Annotation> T getAnnotation(AnnotatedElement target,
			Class<T> annoType) {
		T annotation = target.getAnnotation(annoType);
		if(annotation==null)
			throw new RuntimeException("在"+target+"中未找到"+annoType.getName()+"注解");
		return annotation;
	}

	

	protected ILayoutPresenter getLayoutPresenter(){
		return PresenterManager.getInstance().findPresenter(getContext(),ILayoutPresenterBridge.class);
	}

	protected IAnnotationPresenter getAnnotationPresenter(){
		return PresenterManager.getInstance().findPresenter(getContext(),IAnnotationPresenterBridge.class);
	}

	protected IRequestPresenter getRequester(){
		return PresenterManager.getInstance().findPresenter(getContext(),IRequestPresenterBridge.class);
	}
	
}
