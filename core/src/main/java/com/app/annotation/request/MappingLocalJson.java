package com.app.annotation.request;

import com.app.annotation.Interpreter;
import com.app.presenter.impl.annotation.request.MappingLocalJsonInterpreter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Interpreter(MappingLocalJsonInterpreter.class)
public @interface MappingLocalJson {

	boolean useTempleteJson() default false;
	String fileName();
}
