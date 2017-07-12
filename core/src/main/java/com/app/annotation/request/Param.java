package com.app.annotation.request;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.app.presenter.IRequestPresenter.ParamType;


@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Param{

	String key();
	String value() default "";
	String desc() default "";
	ParamType type() default ParamType.VALUE;
	
}
