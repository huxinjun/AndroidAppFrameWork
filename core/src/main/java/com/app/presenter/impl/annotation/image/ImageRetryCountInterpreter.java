package com.app.presenter.impl.annotation.image;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import com.app.annotation.image.ImageRetryCount;
import com.app.presenter.IImagePresenter.Option;

/**
 * 图片重试解释器
 * @author xinjun
 *
 */
public abstract class ImageRetryCountInterpreter extends ImageAnnotationInterpreter{

	
	@Override
	protected Annotation getImageAnnotation(AnnotatedElement target) {
		return getAnnotation(target, ImageRetryCount.class);
	}

	@Override
	protected void setValue(Option option, Annotation annotation) {
		ImageRetryCount anno=(ImageRetryCount) annotation;
		option.setRetryCount(anno.value());
	}
	
}
