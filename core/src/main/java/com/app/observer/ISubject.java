package com.app.observer;

/**
 * 抽象主题基类
 * 继承此类即为被观察者,可以唤醒注册的观察者
 * @author xinjun
 *
 */
public interface ISubject<T> {

	/**
	 *  
	 */
	public void notifyObserver(T attentionPoint);
}
