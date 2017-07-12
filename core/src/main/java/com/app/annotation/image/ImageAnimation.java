package com.app.annotation.image;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


import com.app.annotation.Interpreter;
import com.app.presenter.impl.annotation.image.ImageAnimationInterpreter;

/**
 * 配置应图片设置时的动画
 * @author XINJUN
 *
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Interpreter(ImageAnimationInterpreter.class)
public @interface ImageAnimation {

	Class<? extends AnimationBuilder> value();
}
