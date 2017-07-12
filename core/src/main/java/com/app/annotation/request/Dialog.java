package com.app.annotation.request;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.app.SmartDialog;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Dialog {

	
	Class<? extends SmartDialog> value();
}
