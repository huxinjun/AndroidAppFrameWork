package com.app.annotation.request;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.app.annotation.Interpreter;
import com.app.presenter.impl.annotation.request.RequestUrlsPackageInterpreter;

/**
 * 配置RequestSetting的类路径
 * 此注解需要配置在请求配置类声明上
 * @author xinjun
 *
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Interpreter(RequestUrlsPackageInterpreter.class)
public @interface RequestUrlsPackage {

	String value();
}
