package com.app.annotation.action;

import com.app.annotation.Interpreter;
import com.app.presenter.impl.annotation.OnClickInterpreter;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 在类型声明上使用此注解,来指示这个类管理的布局所使用的数据类型
 * @author xinjun
 *
 */
@Interpreter(OnClickInterpreter.class)
@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface OnClick {

	String value() default "";
	int viewId() default 0;
}
