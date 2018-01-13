package com.app.annotation.request;

import com.app.annotation.Interpreter;
import com.app.presenter.impl.annotation.request.UseDiscCacheInterpreter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 是否使用磁盘缓存
 * @author XINJUN
 *
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Interpreter(UseDiscCacheInterpreter.class)
public @interface UseDiscCache {
    boolean value() default false;
}
