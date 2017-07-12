package com.app.observer;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * 抽象关注主题
 * 继承此类则该类为被关注的焦点,在条件触发时或者是关注点符合相应条件时将会通知所有的观察者
 * @author xinjun
 *
 */
public class DefaultSubject<T> implements ISubject<T>{

	private Set<IObserver> mAllObservers=new HashSet<IObserver>();
	
	
	/**
	 * 添加一个观察者
	 * @param observer 观察者
	 * @param attachPoint 关注点
	 */
	public void attach(IObserver observer){
		if(mAllObservers.contains(observer))
			return;
		mAllObservers.add(observer);
	}
	
	/**
	 * 去掉一个观察者
	 * @param observer 观察者
	 */
	public void detach(IObserver observer){
		if(mAllObservers.contains(observer))
			mAllObservers.remove(observer);
	}
	
	
	/**
	 * 此类的notifyObserver默认通知所有的观察者
	 */
	@Override
	public void notifyObserver(T attentionPoint) {
		Iterator<IObserver> iterator = mAllObservers.iterator();
		while(iterator.hasNext()){
			IObserver next = iterator.next();
			if(attentionPoint.equals(next.attentionPoint()))
				next.update();
		}
	}
}
