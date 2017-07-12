package com.app.annotation.image;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.app.annotation.Interpreter;
import com.app.presenter.impl.annotation.image.ImageRetryCountInterpreter;

/**
 * 图片重试次数注解
 * @author XINJUN
 *
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Interpreter(ImageRetryCountInterpreter.class)
public @interface ImageRetryCount {

	int value();
}
