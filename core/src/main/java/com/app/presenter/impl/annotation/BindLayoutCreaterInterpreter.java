package com.app.presenter.impl.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;

import android.text.TextUtils;
import android.view.View;

import com.app.annotation.BindFieldName;
import com.app.annotation.creater.BindLayoutCreater;
import com.app.presenter.IInjectionPresenterBridge;
import com.app.presenter.ILayoutPresenter;
import com.app.presenter.ILayoutPresenter.InflateCallBack;
import com.app.presenter.ILayoutPresenterBridge;
import com.app.presenter.IRequestPresenter;
import com.app.presenter.PresenterManager;
import com.app.presenter.impl.layout.LayoutCreater;
import com.app.utils.ReflectUtils;

/**
 * BindLayoutCreater注解解释器
 * Created by xinjun on 2017/7/29 14:33
 */
public class BindLayoutCreaterInterpreter extends AnnotationPresenter{

	@Override
	public void interpreter(AnnotatedElement target,final InterpreterCallBack callBack,Object... context) {
		//这个注解可能会加在类上 或者字段上
		if(target.getClass()==Class.class){
			//加在类上了
			BindLayoutCreater bindLayoutCreater =getAnnotation(target, BindLayoutCreater.class);
			final Class<? extends LayoutCreater> createrClass = bindLayoutCreater.creater();
			final String requestName = bindLayoutCreater.requestName();
			
			try {
				PresenterManager.getInstance().findPresenter(getContext(),ILayoutPresenterBridge.class).inflate(createrClass, new InflateCallBack() {
					
					@Override
					public void onCompleted(LayoutCreater instance) {
						instance.setRequestName(requestName);
						if(callBack!=null)
							callBack.onCompleted(BindLayoutCreater.class,instance);

						IRequestPresenter.ParamPool paramPool= IRequestPresenter.ParamPool.obtain();
						IRequestPresenter.Option option = instance.onBuildRequest(paramPool);
						IRequestPresenter.RequestInfo info=getRequester().build(requestName, option,paramPool);
						if(info!=null)
							info.mCallBack=new IRequestPresenter.DataCallBack() {
								@Override
								public void onDataComming(Object data) {
									instance.setContentData(data);
								}
							};
						getRequester().request(info);
					}
				});
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			
		}else if(target.getClass()==Field.class){
			//加在字段上了
			LayoutCreater creater=(LayoutCreater) context[0];
			Field field= (Field) context[1];
			field.setAccessible(true);
			try {
				//这个View 可能是ListView,GridView,ViewPager等
				View findViewById = (View) field.get(creater);
				BindLayoutCreater itemLayoutCreater = getAnnotation(target, BindLayoutCreater.class);
				//给配置了@BindLayoutCreater注解的视图中放入一些tag记录其子创建器的和其他需要的信息
				findViewById.setTag(LayoutCreater.TAG_LAYOUT_CRETAER_PARENT, creater);
				findViewById.setTag(LayoutCreater.TAG_LAYOUT_CRETAER_ITEM_CLASS, itemLayoutCreater.creater());
				findViewById.setTag(LayoutCreater.TAG_LAYOUT_CRETAER_ITEM_DATA_ID, itemLayoutCreater.requestName());

				//再给其创建请求数据的命令41
				final View finalFindViewById = findViewById;
                Object injectFieldPath = findViewById.getTag(LayoutCreater.TAG_INJECTOR_FIELD);
                String fieldPath=injectFieldPath==null?null:injectFieldPath.toString();
                if(TextUtils.isEmpty(itemLayoutCreater.requestName()) && target.getAnnotation(BindFieldName.class)!=null){
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
				IRequestPresenter.ParamPool paramPool= IRequestPresenter.ParamPool.obtain();
				IRequestPresenter.Option option = creater.onBuildRequest(paramPool);
				IRequestPresenter.RequestInfo info=getRequester().build(itemLayoutCreater.requestName(), option,paramPool);
                if(info!=null)
                    info.mCallBack=new IRequestPresenter.DataCallBack() {
                        @Override
                        public void onDataComming(Object data) {
							BindFieldName bindFieldName=target.getAnnotation(BindFieldName.class);
							Object findObj=data;
							if(!TextUtils.isEmpty(bindFieldName.value()))
								findObj = ReflectUtils.getValueByFieldPath(data, bindFieldName.value());
                            finalFindViewById.setTag(LayoutCreater.TAG_ITEMS_DATA, findObj);
                            //数据来了,这个可能是给listview,gridview,recycleview,viewpager使用的数据
                            PresenterManager.getInstance().findPresenter(getContext(),IInjectionPresenterBridge.class).inject(finalFindViewById, findObj);
                        }
                    };
				getRequester().request(info);
				
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
		ILayoutPresenter.CreaterInfo info=new ILayoutPresenter.CreaterInfo(bindLayoutCreaterAnno.requestName(),bindLayoutCreaterAnno.creater());
		if(callBack!=null)
			callBack.onCompleted(annotation.annotationType(),info);
	}
	
}
