package com.app.annotation.request;

import com.app.annotation.Interpreter;
import com.app.presenter.impl.annotation.request.EntityTypeInterpreter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 给一个Request指定数据类型
 * @author xinjun
 *
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Interpreter(EntityTypeInterpreter.class)
public @interface EntityType{

	Class<?> value();
	
}
