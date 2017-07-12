package com.app.presenter;

import android.graphics.Bitmap;

import com.app.presenter.IImagePresenter.Rect;
import com.app.presenter.impl.PersistentPresenter;

/**
 * 持久化
 * @author xinjun
 *
 */
public class IPersistentPresenterBridge extends IPresenterBridge<IPersistentPresenter> implements IPersistentPresenter{

	@Override
	public void saveObject(String name, Object target) {
		mSource.saveObject(name, target);
	}

	@Override
	public Object getObject(String name,Class<?> clazz) {
		return mSource.getObject(name,clazz);
	}

	@Override
	public void saveObjects(String name, Object... targets) {
		mSource.saveObjects(name, targets);
	}

	@Override
	public Object[] getObjects(String name,Class<?> clazz) {
		return mSource.getObjects(name,clazz);
	}

	@Override
	public String saveBitmap(String url, Bitmap target) {
		return mSource.saveBitmap(url, target);
	}

	@Override
	public Bitmap getBitmap(String url,Rect rect) {
		return mSource.getBitmap(url,rect);
	}

	@Override
	protected IPersistentPresenter deffaultSource() {
		return new PersistentPresenter();
	}

	@Override
	public void setSource(IPersistentPresenter source) {
		mSource=source;
	}


	
}
