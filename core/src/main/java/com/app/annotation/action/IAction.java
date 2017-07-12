package com.app.annotation.action;

import com.app.presenter.impl.LayoutPresenter.LayoutCreater;

/**
 * 控制注解执行的时间,如果一些注解支持action属性,那么首先会调用action声明的业务类
 * 然后还会传入注解需要执行的操作,在action中进行手动调用
 * @author xinjun
 *
 */
public interface IAction {

	public void onAction(LayoutCreater creater,int viewID,Runnable annoRunnable);
}
