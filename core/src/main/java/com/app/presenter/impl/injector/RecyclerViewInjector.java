package com.app.presenter.impl.injector;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.app.SmartRecyclerAdapter;
import com.app.SmartViewPagerSameTemplaterAdapter;
import com.app.ULog;

public class RecyclerViewInjector extends ViewInjector {


	@Override
	public void inject(View target, Object value) {
		RecyclerView recyclerView=(RecyclerView) target;
		if(value!=null)
			recyclerView.setAdapter(new SmartRecyclerAdapter(getContext(),recyclerView));
	}

}
