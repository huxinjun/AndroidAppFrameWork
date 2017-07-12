package com.app.presenter;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.app.presenter.impl.ImagePresenter;

/**
 * 图片加载代理类
 * @author xinjun
 *
 */
public class IImagePresenterBridge extends IPresenterBridge<IImagePresenter> implements IImagePresenter{


	@Override
	protected IImagePresenter deffaultSource() {
		return new ImagePresenter();
	}

	@Override
	public void setSource(IImagePresenter source) {
		mSource=source;
	}

	@Override
	public void setGlobleOption(Option option) {
		mSource.setGlobleOption(option);
	}

	@Override
	public Bitmap getImage(String url, OnImageDownloadOkListener listener) {
		return mSource.getImage(url, listener);
	}

	@Override
	public void setImage(ImageView target, String url) {
		mSource.setImage(target, url);
	}

	@Override
	public void setMemorySize(long size) {
		mSource.setMemorySize(size);
	}

	@Override
	public Option getGlobleOption() {
		return mSource.getGlobleOption();
	}

	@Override
	public Bitmap compressImage(String imageUrl, Rect rect) {
		return mSource.compressImage(imageUrl, rect);
	}


	

	
}
