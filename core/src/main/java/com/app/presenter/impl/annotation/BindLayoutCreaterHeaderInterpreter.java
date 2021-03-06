package com.app.presenter.impl.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.app.annotation.creater.BindLayoutCreaterHeader;
import com.app.presenter.ILayoutPresenter.InflateCallBack;
import com.app.presenter.impl.layout.LayoutCreater;

public class BindLayoutCreaterHeaderInterpreter extends AnnotationPresenter{

	@Override
	public void interpreter(AnnotatedElement target,InterpreterCallBack callBack,Object... context) {
		LayoutCreater creater=(LayoutCreater) context[0];
		Field field= (Field) context[1];
		field.setAccessible(true);
		try {
			View findViewById = (View) field.get(creater);
			BindLayoutCreaterHeader bindHeaderLayoutCreater =getAnnotation(target, BindLayoutCreaterHeader.class);
			Class<? extends LayoutCreater> headerCreater = bindHeaderLayoutCreater.creater();
			//这个视图肯定是一个list,给他配置了一个header
			if(!(findViewById instanceof ListView))
				return;
			final ListView lv_content=(ListView) findViewById;
			lv_content.setTag(LayoutCreater.TAG_LAYOUT_CRETAER_HEADER_CLASS, bindHeaderLayoutCreater.creater());
			
			
			getLayoutPresenter().inflate(headerCreater, new InflateCallBack() {
				
				@Override
				public void onCompleted(LayoutCreater instance) {
					//Header的LayoutCreater创建成功
					lv_content.setTag(LayoutCreater.TAG_LAYOUT_CRETAER_HEADER, instance);
					lv_content.addHeaderView(instance.getContentView());
//					lv_content.setAdapter(new BaseAdapter() {
//						@Override
//						public View getView(int position, View convertView, ViewGroup parent) {
//							return null;
//						}
//
//						@Override
//						public long getItemId(int position) {
//							return 0;
//						}
//
//						@Override
//						public Object getItem(int position) {
//							return null;
//						}
//
//						@Override
//						public int getCount() {
//							return 0;
//						}
//					});
					
				}
			});
			
			
		} catch (Exception e) {
			//出错误说明没有配置这个注解,不用管
		}
	}

	@Override
	public void interpreter(Annotation annotation,
			InterpreterCallBack callBack, Object... context) {
		// TODO Auto-generated method stub
		
	}
	
}
