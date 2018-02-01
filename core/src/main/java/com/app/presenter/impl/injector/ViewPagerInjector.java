package com.app.presenter.impl.injector;

import android.support.v4.view.ViewPager;
import android.view.View;

import com.app.SmartViewPagerSameTemplaterAdapter;
import com.app.presenter.IInjectionPresenter;

public class ViewPagerInjector extends ViewInjector {


	@Override
	public void inject(View target, Object value) {
		//ViewPager每一页都是一样的
		ViewPager pagerView=(ViewPager) target;
		if(value==null)
			pagerView.setAdapter(null);
		else
			pagerView.setAdapter(new SmartViewPagerSameTemplaterAdapter(getContext(),pagerView));
	}

}
