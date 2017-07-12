package com.app.presenter.impl.annotation.image;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import com.app.annotation.image.ImageCacheInMem;
import com.app.presenter.IImagePresenter.Option;

/**
 * 图片缓存到内存解释器
 * @author xinjun
 *
 */
public class ImageCacheInMemInterpreter extends ImageAnnotationInterpreter{


	
	@Override
	protected Annotation getImageAnnotation(AnnotatedElement target) {
		return getAnnotation(target, ImageCacheInMem.class);
	}

	@Override
	protected void setValue(Option option, Annotation annotation) {
		ImageCacheInMem anno=(ImageCacheInMem) annotation;
		option.setIsCacheInMem(anno.value());
	}
	
}
