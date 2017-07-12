package com.app.annotation.fragment;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.app.annotation.action.IAction;
import com.app.presenter.IAnnotationPresenter;
import com.app.presenter.impl.annotation.AnnotationPresenter;

/**
 * 在类型声明上使用此注解,来指示这个类管理的布局所使用的数据类型
 * @author xinjun
 *
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ChangeFragments {

	Fragment[] fragments();   
	
	Class<? extends IAction> action() default IAction.class;
}
