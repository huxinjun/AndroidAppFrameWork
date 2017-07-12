package com.app.annotation.image;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.app.annotation.Interpreter;
import com.app.presenter.impl.annotation.image.ImageWaittingResourceInterpreter;

/**
 * 图片正在下载时显示的资源id
 * @author XINJUN
 *
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Interpreter(ImageWaittingResourceInterpreter.class)
public @interface ImageWaittingResource {

	int value();
}
