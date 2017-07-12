package com.app.presenter.impl.injector;

import android.support.v4.view.ViewPager;
import android.view.View;

import com.app.SmartViewPagerSameTemplaterAdapter;
import com.app.presenter.IInjectionPresenter;

public class ViewPagerInjector implements IInjectionPresenter {


	@Override
	public void inject(View target, Object value) {
		//ViewPager每一页都是一样的
		ViewPager pagerView=(ViewPager) target;
		pagerView.setAdapter(new SmartViewPagerSameTemplaterAdapter(pagerView));
	}

}
