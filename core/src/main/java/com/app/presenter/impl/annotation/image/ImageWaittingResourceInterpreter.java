package com.app.presenter.impl.annotation.image;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import com.app.annotation.image.ImageWaittingResource;
import com.app.presenter.IImagePresenter.Option;

/**
 * 图片下载时显示的资源id解释器
 * @author xinjun
 *
 */
public class ImageWaittingResourceInterpreter extends ImageAnnotationInterpreter{

	@Override
	protected Annotation getImageAnnotation(AnnotatedElement target) {
		return getAnnotation(target, ImageWaittingResource.class);
	}

	@Override
	protected void setValue(Option option, Annotation annotation) {
		ImageWaittingResource anno=(ImageWaittingResource) annotation;
		option.setWaittingResId(anno.value());
	}

	
}
