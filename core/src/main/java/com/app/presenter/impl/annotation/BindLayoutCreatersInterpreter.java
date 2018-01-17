package com.app.presenter.impl.annotation;

import android.support.v4.view.ViewPager;
import android.view.View;

import com.app.SmartViewPagerAdapter;
import com.app.annotation.Injector;
import com.app.annotation.creater.BindLayoutCreater;
import com.app.annotation.creater.BindLayoutCreaters;
import com.app.presenter.IInjectionPresenter;
import com.app.presenter.ILayoutPresenter;
import com.app.presenter.impl.layout.LayoutCreater;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * @BindLayoutCreaters注解解释器
 * @author xinjun
 *
 */
public class BindLayoutCreatersInterpreter extends AnnotationPresenter{

	@Override
	public void interpreter(AnnotatedElement target,InterpreterCallBack callBack,Object... context) {
		final LayoutCreater creater=(LayoutCreater) context[0];
		Field field= (Field) context[1];
		field.setAccessible(true);
		if(target.getClass()==Field.class){
			
			final BindLayoutCreaters annotation =getAnnotation(target, BindLayoutCreaters.class);
			BindLayoutCreater[] creaters = annotation.value();
			final ArrayList<ILayoutPresenter.CreaterInfo> createrInfos=new ArrayList<>();
			if(creaters!=null && creaters.length>0){
				for(BindLayoutCreater anno:creaters)
					getAnnotationPresenter().interpreter(anno, new InterpreterCallBack() {
						@Override
						public void onCompleted(Class<? extends Annotation> anno, Object... results) {
							createrInfos.add((ILayoutPresenter.CreaterInfo) results[0]);
						}
					});
			}
			//解释完毕后
			try {
				ViewPager viewpager = (ViewPager) field.get(creater);
				//设置Adapter
				viewpager.setAdapter(new SmartViewPagerAdapter(getContext(),createrInfos));

			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	@Override
	public void interpreter(Annotation annotation,InterpreterCallBack callBack, Object... context) {
		//Ignore
	}
	
}