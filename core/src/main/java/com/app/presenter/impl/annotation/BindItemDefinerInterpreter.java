package com.app.presenter.impl.annotation;

import android.view.View;

import com.app.annotation.BindFieldName;
import com.app.annotation.creater.BindItemDefiner;
import com.app.annotation.creater.BindLayoutCreater;
import com.app.presenter.IInjectionPresenterBridge;
import com.app.presenter.ILayoutPresenter;
import com.app.presenter.ILayoutPresenter.InflateCallBack;
import com.app.presenter.ILayoutPresenterBridge;
import com.app.presenter.PresenterManager;
import com.app.presenter.impl.layout.LayoutCreater;
import com.app.utils.ReflectUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;

/**
 * BindLayoutCreater注解解释器
 * Created by xinjun on 2017/7/29 14:33
 */
public class BindItemDefinerInterpreter extends AnnotationPresenter{

	@Override
	public void interpreter(AnnotatedElement target,final InterpreterCallBack callBack,Object... context) {
		if(target.getClass()==Field.class){
			//加在字段上了
			LayoutCreater creater=(LayoutCreater) context[0];
			Field field= (Field) context[1];
			field.setAccessible(true);
			try {
				//这个View 可能是ListView,GridView,ViewPager等
				View findViewById = (View) field.get(creater);
				BindItemDefiner definer = getAnnotation(target, BindItemDefiner.class);
				//给配置了@BindLayoutCreater注解的视图中放入一些tag记录其子创建器的和其他需要的信息
				findViewById.setTag(LayoutCreater.TAG_LAYOUT_CRETAER_PARENT, creater);
				findViewById.setTag(LayoutCreater.TAG_DEFINER_ITEM_CLASS, definer.value());

				//再给其创建请求数据的命令41
				final View finalFindViewById = findViewById;
                if(definer!=null && target.getAnnotation(BindFieldName.class)!=null){
                    //如果BindLayoutCreater没有配置itemLayoutCreater.requestName()，配置了BindFieldName注解，说明需要从父LayoutCreater的数据中取数据
					creater.addDataListener(new LayoutCreater.DataListener() {
						@Override
						public void onDataPrepared(Object data) {
							BindFieldName bindFieldName=target.getAnnotation(BindFieldName.class);
							//根据BindFieldName在父LayoutCreater关联的数据中找自己需要的字段
							Object findObj = ReflectUtils.getValueByFieldPath(data, bindFieldName.value());
							finalFindViewById.setTag(LayoutCreater.TAG_ITEMS_DATA, findObj);
							//数据来了,这个可能是给listview,gridview,recycleview,viewpager使用的数据
							PresenterManager.getInstance().findPresenter(getContext(),IInjectionPresenterBridge.class).inject(finalFindViewById, findObj);
						}
					});
                    return;
                }
			} catch (Exception e) {
				//出错误说明没有配置这个注解,不用管
				throw new RuntimeException(e);
			}
		}
	}

	@Override
	public void interpreter(Annotation annotation,
			InterpreterCallBack callBack, Object... context) {
		//嵌套的，ViewPager能配置BindLayoutCreaters注解
		BindLayoutCreater bindLayoutCreaterAnno= (BindLayoutCreater) annotation;
		ILayoutPresenter.CreaterInfo info=new ILayoutPresenter.CreaterInfo(bindLayoutCreaterAnno.creater());
		if(callBack!=null)
			callBack.onCompleted(annotation.annotationType(),info);
	}
	
}
