package com.app.presenter;


import android.content.Context;

/**
 * 业务代理类
 * 设计模式：桥接模式
 * 需求：一个业务类会有多个实现，而在程序运行中具体要执行那个实现类我们不知道，为了解决动态的选择和使用具体业务的某一个实现类
 * 解决办法：让桥接类实现业务接口，然后持有一个具体业务类的实现（程序运行中可以替换这个实现，初次创建使用默认提供的实现），在调用桥接类重写的业务接口时手动调用目标实现类的该方法
 * @author xinjun
 *
 */
public abstract class IPresenterBridge<T extends IPresenter> implements IPresenter{

	protected Context mContext;
	/**
	 * 目标实现类
	 */
	protected T mSource;
	
	/**
	 * 默认的目标实现类
	 * @return
	 */
	protected abstract T deffaultSource();
	

	@Override
	public void setContext(Context context) {
		mContext=context;
	}

	@Override
	public Context getContext() {
		return mContext;
	}

	public void setSource(T source) {
		mSource=source;
		mSource.setContext(mContext);
	}
}
