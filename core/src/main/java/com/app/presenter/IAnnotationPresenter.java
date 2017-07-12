package com.app.presenter;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;

/**
 * Annotation解释器接口
 * 包含所有关于注解逻辑的接口
 * @author xinjun
 *
 */
public interface IAnnotationPresenter extends IPresenter {
	/**
	 * Annotation解释器回调接口
	 * @author xinjun
	 *
	 */
	public static interface InterpreterCallBack{
		/**
		 * 每一个Annotation解释完毕后将会发起一次回调
		 * @param anno 当前刚解释完的Annotation
		 * @param results 返回的结果,可有可无
		 */
		void onCompleted(Class<? extends Annotation> anno,Object... results);
	}
	
	/**
	 * 根据给定的字段或者类型解释加载在其上的所有注解
	 * @param target 要被解释的目标可以使class,field等等可以声明Annotation任何对象
	 * @param callBack 每当发现一个注解解释完成后会发起回调
	 * @param context 上下文环境
	 */
	public void interpreter(AnnotatedElement target,InterpreterCallBack callBack,Object... context);
	
	/**
	 * 给定一个注解对象进行解释(适用于注解参数中的嵌套注解)
	 * @param annotation 注解
	 * @param callBack 解释回调
	 * @param context 上下文环境
	 */
	public void interpreter(Annotation annotation,InterpreterCallBack callBack,Object... context);
	
	
	/**
	 * 获取一个class上配置的某个类型的注解,如果没有发现会抛出RuntimeException
	 * @param target 配置注解的目标
	 * @param annoType 注解类型
	 * @return 注解对象
	 */
	public abstract <T extends Annotation> T getAnnotation(AnnotatedElement target,Class<T> annoType);

}
