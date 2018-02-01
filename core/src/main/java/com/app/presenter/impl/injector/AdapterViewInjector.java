package com.app.presenter.impl.injector;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;

import com.app.SmartAbsListAdapter;
import com.app.presenter.IInjectionPresenter;

import java.lang.ref.WeakReference;

public class AdapterViewInjector extends ViewInjector {

	@SuppressWarnings("unchecked")
	@Override
	public void inject(View target, Object value) {
		//设置adapter
		AdapterView<BaseAdapter> adapterView=(AdapterView<BaseAdapter>) target;
		if(value==null)
			adapterView.setAdapter(null);
		else
			adapterView.setAdapter(new SmartAbsListAdapter(getContext(),adapterView));
	}

}
