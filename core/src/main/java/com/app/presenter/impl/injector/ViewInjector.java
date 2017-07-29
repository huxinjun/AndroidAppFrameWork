package com.app.presenter.impl.injector;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;

import com.app.SmartAbsListAdapter;
import com.app.presenter.IInjectionPresenter;

import java.lang.ref.WeakReference;

public class ViewInjector implements IInjectionPresenter {

	private WeakReference<Context> mContext;

	@Override
	public void setContext(Context context) {
		mContext=new WeakReference<Context>(context);
	}

	@Override
	public Context getContext() {
		return mContext.get();
	}

	@Override
	public void inject(View target, Object value) {

		//设置adapter
		@SuppressWarnings("unchecked")
		AdapterView<BaseAdapter> adapterView=(AdapterView<BaseAdapter>) target;
		adapterView.setAdapter(new SmartAbsListAdapter(getContext(),adapterView));
	}

}
