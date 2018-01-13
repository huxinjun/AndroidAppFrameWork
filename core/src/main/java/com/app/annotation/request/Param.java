package com.app.annotation.request;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.app.annotation.Interpreter;
import com.app.presenter.IRequestPresenter.ParamType;
import com.app.presenter.impl.annotation.request.ParamInterpreter;


@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Interpreter(ParamInterpreter.class)
public @interface Param{

	String key();
	String value() default "";
	String desc() default "";
	ParamType type() default ParamType.VALUE;
	
}
