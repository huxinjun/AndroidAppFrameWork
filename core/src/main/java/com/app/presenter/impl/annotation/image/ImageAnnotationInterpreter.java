package com.app.presenter.impl.annotation.image;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.Type;

import android.widget.ImageView;

import com.app.presenter.IImagePresenterBridge;
import com.app.presenter.PresenterManager;
import com.app.presenter.IImagePresenter.Option;
import com.app.presenter.impl.LayoutPresenter.LayoutCreater;
import com.app.presenter.impl.annotation.AnnotationPresenter;

/**
 * 图片加载动画解释器
 * @author xinjun
 *
 */
public abstract class ImageAnnotationInterpreter extends AnnotationPresenter{

	@Override
	public void interpreter(AnnotatedElement target,InterpreterCallBack callBack,Object... context) {
		LayoutCreater creater=(LayoutCreater) context[0];
		int viewid=(Integer) context[1];
		ImageView targetImage=(ImageView) creater.getContentView().findViewById(viewid);
		Annotation anno = getImageAnnotation(target);
		Option option = null;
		if(target.getClass()==Field.class){
			//配置在LayoutCreater中的字段上
			option = (Option) targetImage.getTag(LayoutCreater.TAG_IMAGE_OPTION);
			if(option==null)
				try {
					targetImage.setTag(LayoutCreater.TAG_IMAGE_OPTION, PresenterManager.getInstance().findPresenter(IImagePresenterBridge.class).getGlobleOption().clone());
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
				}
			option = (Option) targetImage.getTag(LayoutCreater.TAG_IMAGE_OPTION);
			
		}else if(target.getClass()==Type.class){
			//配置在类上，一般会配置在Application上
			option = PresenterManager.getInstance().findPresenter(IImagePresenterBridge.class).getGlobleOption();
		}
		setValue(option, anno);
	}
	
	protected abstract Annotation getImageAnnotation(AnnotatedElement target);

	protected abstract void setValue(Option option,Annotation annotation);
	
	@Override
	public void interpreter(Annotation annotation,InterpreterCallBack callBack, Object... context) {
		//Ignore
	}
	
}
