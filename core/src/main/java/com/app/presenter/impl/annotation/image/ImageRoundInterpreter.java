package com.app.presenter.impl.annotation.image;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;

import com.app.annotation.image.ImageRound;
import com.app.presenter.IImagePresenter.Option;

/**
 * 图片圆角解释器
 * @author xinjun
 *
 */
public class ImageRoundInterpreter extends ImageAnnotationInterpreter{

	@Override
	protected Annotation getImageAnnotation(AnnotatedElement target) {
		return getAnnotation(target, ImageRound.class);
	}

	@Override
	protected void setValue(Option option, Annotation annotation) {
		ImageRound anno=(ImageRound) annotation;
		option.setRoundPix(anno.value());
	}
}
