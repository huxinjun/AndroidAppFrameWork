package com.app.annotation.request;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.app.annotation.Interpreter;
import com.app.presenter.impl.annotation.request.LocalJsonPackageInterpreter;

/**
 * 配置json协议文件所在的包名，用于测试阶段读取模板数据
 * 配置在url字段类声明上
 * @author XINJUN
 *
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Interpreter(LocalJsonPackageInterpreter.class)
public @interface LocalJsonPackage {

	String value();
}
