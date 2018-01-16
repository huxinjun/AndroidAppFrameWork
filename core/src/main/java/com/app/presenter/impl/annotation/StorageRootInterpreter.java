package com.app.presenter.impl.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;

import com.app.annotation.storage.StorageRoot;
import com.app.presenter.IAnnotationPresenter;
import com.app.presenter.IStoragePresenterBridge;
import com.app.presenter.PresenterManager;

/**
 * 配置存储根目录注解
 * @author xinjun
 *
 */
public class StorageRootInterpreter extends AnnotationPresenter{

	@Override
	public void interpreter(AnnotatedElement target,InterpreterCallBack callBack,Object... context) {
		StorageRoot injector =getAnnotation(target, StorageRoot.class);
		PresenterManager.getInstance().findPresenter(getContext(),IStoragePresenterBridge.class).setRoot(injector.value());
	}

	@Override
	public void interpreter(Annotation annotation,InterpreterCallBack callBack, Object... context) {
		//Ignore
	}
	
}
