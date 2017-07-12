package com.app.presenter.impl.annotation.image;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import com.app.annotation.image.ImageRect;
import com.app.presenter.IImagePresenter.Option;
import com.app.presenter.IImagePresenter.Rect;

/**
 * 图片加载动画解释器
 * @author xinjun
 *
 */
public class ImageRectInterpreter extends ImageAnnotationInterpreter{


	@Override
	protected Annotation getImageAnnotation(AnnotatedElement target) {
		return getAnnotation(target, ImageRect.class);
	}

	@Override
	protected void setValue(Option option, Annotation annotation) {
		ImageRect anno=(ImageRect) annotation;
		option.setRect(new Rect(anno.width(), anno.height()));
	}
	
}
