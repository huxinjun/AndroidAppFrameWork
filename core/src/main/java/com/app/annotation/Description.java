package com.app.annotation;

import com.app.presenter.impl.annotation.request.DescriptionInterpreter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Interpreter(DescriptionInterpreter.class)
public @interface Description {

	String value();
}
