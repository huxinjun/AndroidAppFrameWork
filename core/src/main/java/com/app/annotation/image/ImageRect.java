package com.app.annotation.image;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


import com.app.annotation.Interpreter;
import com.app.presenter.impl.annotation.image.ImageRectInterpreter;

/**
 * 配置应图片最大显示空间
 * @author XINJUN
 *
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Interpreter(ImageRectInterpreter.class)
public @interface ImageRect {

	int width();
	int height();
}
