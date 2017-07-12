package com.app.annotation.storage;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.app.annotation.Interpreter;
import com.app.presenter.impl.annotation.StorageRootInterpreter;

/**
 * 配置应用程序的存储根目录
 * @author XINJUN
 *
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Interpreter(StorageRootInterpreter.class)
public @interface StorageRoot {

	String value();
}
