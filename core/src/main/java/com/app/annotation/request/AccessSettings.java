package com.app.annotation.request;

import com.app.annotation.Interpreter;
import com.app.presenter.impl.annotation.request.AccessSettingInterpreter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Interpreter(AccessSettingInterpreter.class)
public @interface AccessSettings {

	/**
	 * 请求方式
	 * @author xinjun
	 *
	 */
	public static enum RequestMethods{
		GET,POST
	}
	RequestMethods accessMethod() default RequestMethods.GET;
	boolean showDialog() default true;
	boolean persistence() default true;
	
}
