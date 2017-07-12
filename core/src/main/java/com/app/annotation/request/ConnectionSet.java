package com.app.annotation.request;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ConnectionSet {

	int connectionTimeOut() default 3000;
	int serverTimeOut() default 3000;
	int retryCount() default 1;
}
