package com.app.annotation.request;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.app.SmartDialog;
import com.app.annotation.Interpreter;
import com.app.presenter.impl.annotation.request.DialogInterpreter;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Interpreter(DialogInterpreter.class)
public @interface Dialog {

	
	Class<? extends SmartDialog> value();
}
