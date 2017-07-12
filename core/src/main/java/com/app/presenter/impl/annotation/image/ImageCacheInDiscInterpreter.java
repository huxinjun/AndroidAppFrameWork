package com.app.presenter.impl.annotation.image;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import com.app.annotation.image.ImageCacheInDisc;
import com.app.presenter.IImagePresenter.Option;

/**
 * 图片缓存到磁盘解释器
 * @author xinjun
 *
 */
public class ImageCacheInDiscInterpreter extends ImageAnnotationInterpreter{

	@Override
	protected Annotation getImageAnnotation(AnnotatedElement target) {
		return getAnnotation(target, ImageCacheInDisc.class);
	}

	@Override
	protected void setValue(Option option, Annotation annotation) {
		ImageCacheInDisc anno=(ImageCacheInDisc) annotation;
		option.setIsCacheInDisc(anno.value());
	}
	
}
