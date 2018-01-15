package com.app.presenter.impl.injector;

import android.view.View;
import android.widget.TextView;

import com.app.presenter.IInjectionPresenter;

public class TextViewInjector extends ViewInjector {


	@Override
	public void inject(View target, Object value) {
		TextView tv= (TextView) target;
		tv.setText(value.toString());
	}

}
