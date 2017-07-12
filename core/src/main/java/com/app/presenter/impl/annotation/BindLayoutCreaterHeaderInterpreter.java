package com.app.presenter.impl.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.app.annotation.creater.BindLayoutCreaterHeader;
import com.app.presenter.ILayoutPresenter.InflateCallBack;
import com.app.presenter.impl.LayoutPresenter.LayoutCreater;

public class BindLayoutCreaterHeaderInterpreter extends AnnotationPresenter{

	@Override
	public void interpreter(AnnotatedElement target,InterpreterCallBack callBack,Object... context) {
		LayoutCreater parentCreater=(LayoutCreater) context[0];
		int viewid=(Integer) context[1];
		try {
			BindLayoutCreaterHeader bindHeaderLayoutCreater =getAnnotation(target, BindLayoutCreaterHeader.class);
			Class<? extends LayoutCreater> headerCreater = bindHeaderLayoutCreater.creater();
			//这个视图肯定是一个list,给他配置了一个header
			final View findViewById = parentCreater.getContentView().findViewById(viewid);
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
					lv_content.setAdapter(new BaseAdapter() {
						@Override
						public View getView(int position, View convertView, ViewGroup parent) {
							return null;
						}
						
						@Override
						public long getItemId(int position) {
							return 0;
						}
						
						@Override
						public Object getItem(int position) {
							return null;
						}
						
						@Override
						public int getCount() {
							return 0;
						}
					});
					
				}
			});
			
			
		} catch (RuntimeException e) {
			//出错误说明没有配置这个注解,不用管
		}
	}

	@Override
	public void interpreter(Annotation annotation,
			InterpreterCallBack callBack, Object... context) {
		// TODO Auto-generated method stub
		
	}
	
}
