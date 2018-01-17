package com.app.presenter.impl.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;

import android.text.TextUtils;
import android.view.View;
import com.app.annotation.creater.BindLayoutCreater;
import com.app.presenter.IFragmentPresenter;
import com.app.presenter.IInjectionPresenterBridge;
import com.app.presenter.ILayoutPresenter;
import com.app.presenter.ILayoutPresenter.InflateCallBack;
import com.app.presenter.ILayoutPresenterBridge;
import com.app.presenter.PresenterManager;
import com.app.presenter.IDataPresenter.DataInnerCallBack;
import com.app.presenter.IDataPresenter.RequestDataCommand;
import com.app.presenter.impl.layout.LayoutCreater;

import static com.app.presenter.impl.layout.LayoutCreater.TAG_INJECTOR_FIELD;

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
						instance.onCreated();
						if(callBack!=null)
							callBack.onCompleted(BindLayoutCreater.class,instance);
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

				//再给其创建请求数据的命令
				final View finalFindViewById = findViewById;
                Object injectFieldPath = findViewById.getTag(LayoutCreater.TAG_INJECTOR_FIELD);
                String fieldPath=injectFieldPath==null?null:injectFieldPath.toString();
                getDataPresenter().sendRequestDataCommand(new RequestDataCommand(itemLayoutCreater.requestName(),fieldPath, new DataInnerCallBack() {
					
					@Override
					public void onDataComming(RequestDataCommand command,Object data) {
						finalFindViewById.setTag(LayoutCreater.TAG_ITEMS_DATA, data);
						//数据来了,这个可能是给listview,gridview,recycleview,viewpager使用的数据
						PresenterManager.getInstance().findPresenter(getContext(),IInjectionPresenterBridge.class).inject(finalFindViewById, data);
					}
				}).setType(RequestDataCommand.TYPE_LIST_OBJECT));
				
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
