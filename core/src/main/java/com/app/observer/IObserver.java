package com.app.observer;

/**
 * 观察者的抽象接口
 * 实现这个接口将可以添加到某一个具体的主题管理类中(继承了IDefaultSubject的类)
 * 当触发条件时某一个观察者或者一些将可能会受到update方法(取决于注册时给定的关注点对象)
 * @author xinjun
 *
 */
public interface IObserver {

	/**
	 * 关注点
	 * @return
	 */
	public Object attentionPoint();
	
	public void update();
}
