package com.app.annotation.image;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.app.annotation.Interpreter;
import com.app.presenter.impl.annotation.image.ImageRoundInterpreter;

/**
 * 图片圆角
 * @author XINJUN
 *
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Interpreter(ImageRoundInterpreter.class)
public @interface ImageRound {

	int value();
}
