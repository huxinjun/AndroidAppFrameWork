package com.app.annotation.image;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.app.annotation.Interpreter;
import com.app.presenter.impl.annotation.image.ImageCacheInDiscInterpreter;

/**
 * 配置图片加载是否需要缓存到磁盘
 * @author XINJUN
 *
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Interpreter(ImageCacheInDiscInterpreter.class)
public @interface ImageCacheInDisc {

	boolean value();
}
