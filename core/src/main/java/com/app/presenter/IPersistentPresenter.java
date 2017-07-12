package com.app.presenter;

import com.app.presenter.IImagePresenter.Rect;

import android.graphics.Bitmap;

/**
 * 持久化接口
 * @author xinjun
 *
 */
public interface IPersistentPresenter extends IPresenter {

	public abstract void saveObject(String name,Object target);
	
	public abstract Object getObject(String name,Class<?> clazz);
	
	public abstract void saveObjects(String name,Object...targets);

	public abstract Object[] getObjects(String name,Class<?> clazz);
	
	public abstract String saveBitmap(String url,Bitmap target);
	
	public abstract Bitmap getBitmap(String url,Rect rect);
}
