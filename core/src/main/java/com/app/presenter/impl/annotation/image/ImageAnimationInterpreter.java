package com.app.presenter.impl.annotation.image;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import com.app.annotation.image.AnimationBuilder;
import com.app.annotation.image.ImageAnimation;
import com.app.presenter.IImagePresenter.Option;

/**
 * 图片加载动画解释器
 * @author xinjun
 *
 */
public class ImageAnimationInterpreter extends ImageAnnotationInterpreter{


	@Override
	protected Annotation getImageAnnotation(AnnotatedElement target) {
		return getAnnotation(target, ImageAnimation.class);
	}

	@Override
	protected void setValue(Option option, Annotation annotation) {
		try {
			ImageAnimation anno=(ImageAnimation) annotation;
			AnimationBuilder animationBuilder = anno.value().newInstance();
			option.setAnimation(animationBuilder.build());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
