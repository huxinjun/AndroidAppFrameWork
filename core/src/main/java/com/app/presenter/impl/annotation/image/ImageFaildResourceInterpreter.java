package com.app.presenter.impl.annotation.image;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import com.app.annotation.image.ImageFaildResource;
import com.app.presenter.IImagePresenter.Option;

/**
 * 图片加载失败解释器
 * @author xinjun
 *
 */
public class ImageFaildResourceInterpreter extends ImageAnnotationInterpreter{

	
	@Override
	protected Annotation getImageAnnotation(AnnotatedElement target) {
		return getAnnotation(target, ImageFaildResource.class);
	}

	@Override
	protected void setValue(Option option, Annotation annotation) {
		ImageFaildResource anno=(ImageFaildResource) annotation;
		option.setFaildResId(anno.value());
	}
}
