package com.app.presenter.impl.injector;

import android.view.View;
import android.widget.ImageView;

import com.app.ULog;
import com.app.presenter.IImagePresenterBridge;
import com.app.presenter.IInjectionPresenter;
import com.app.presenter.PresenterManager;

public class ImageViewInjector extends ViewInjector {


	@Override
	public void inject(View target, Object value) {
		value=value.toString().startsWith("http")?value:"https://xzbenben.cn/AccountBook/image/get/"+value;
//		ULog.out("ImageViewInjector.inject:"+value);
		PresenterManager.getInstance().findPresenter(getContext(), IImagePresenterBridge.class).setImage((ImageView) target,value.toString());
	}

}
