package com.app.annotation.request;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.app.annotation.Interpreter;
import com.app.presenter.impl.annotation.request.DatasPackageInterpreter;

/**
 * 配置RequestSetting的类路径
 * 此注解需要配置在AndroidProgram的Application类上
 * @author xinjun
 *
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Interpreter(DatasPackageInterpreter.class)
public @interface DatasDeclareClass {

	Class value();
}
