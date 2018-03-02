package com.app.presenter;


import android.view.View;

import com.app.presenter.impl.layout.LayoutCreater;

/**
 * 布局管理类
 * 主要负责:
 * 1.加载布局(一个id和加载改布局的对象对应一个布局)
 * 2.为布局中的控件的点击或者其他事件添加映射(通过注解)
 * 3.为创建的布局中的视图添加关注点(通过注解)
 * @author xinjun
 *
 */
public interface ILayoutPresenter extends IPresenter {

	/**
	 * 绑定一系列的LayoutCreater需要的信息
	 * @author xinjun
	 *
	 */
	class CreaterInfo{
		public String requestName;
		public Class<? extends LayoutCreater> clazz;
		public CreaterInfo(Class<? extends LayoutCreater> clazz) {
			super();
			this.clazz = clazz;
		}


	}

	/**
	 * 实例创建后的回调
	 * 为什么不用返回值而要使用回调?
	 * 答:由于创建实例取决于给定class上的Annotation,然而一个class上可能配置了多个Annotation,
	 * Annotation解释器会根据给定的class自动挨个解释所有的注解,这样的话就不能使用返回值了(方法只能解决返回单个值的问题)
	 * Annotation解释器使用的是回调的方式,所以inflate方法也只能使用回调的方式了^_^
	 * @author xinjun
	 *
	 */
	public interface InflateCallBack{
		void onCompleted(LayoutCreater instance);
	}

	/**
	 * 根据给定的class,实例化出一个LayoutCreater,这个LayoutCreater中包含所有的View信息
	 * @param createrClass 配置了@BindLayoutCreater注解的class
	 * @param callBack 当注解解释成功并根据注解创建了相应的LayoutCreate时发起的回调,回调中方可拿到LayoutCreater实例
	 */
	public void inflate(Class<? extends LayoutCreater> createrClass,InflateCallBack callBack);
	
	/**
	 * 寻找一个creater下的某个视图
	 * @param creater creater实例
	 * @param viewId 视图id
	 * @return
	 */
	public abstract View findView(LayoutCreater creater,int viewId);

}
