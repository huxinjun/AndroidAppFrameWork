package com.app.annotation.request;

import com.app.annotation.Interpreter;
import com.app.presenter.impl.annotation.request.RequestBaseUrlInterpreter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Interpreter(RequestBaseUrlInterpreter.class)
public @interface RequestBaseUrl {

	String value();
}
