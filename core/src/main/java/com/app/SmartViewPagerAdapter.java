package com.app;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.app.presenter.ILayoutPresenter;
import com.app.presenter.ILayoutPresenter.InflateCallBack;
import com.app.presenter.ILayoutPresenterBridge;
import com.app.presenter.IRequestPresenter;
import com.app.presenter.IRequestPresenterBridge;
import com.app.presenter.PresenterManager;
import com.app.presenter.impl.layout.LayoutCreater;

import java.util.List;

public class SmartViewPagerAdapter extends PagerAdapter {

	List<ILayoutPresenter.CreaterInfo> mCreaterInfos;


	private Context context;

	@SuppressWarnings("unchecked")
	public SmartViewPagerAdapter(Context context, List<ILayoutPresenter.CreaterInfo> createrInfos) {
		this.context=context;
		mCreaterInfos=createrInfos;
	}

	@Override
	public int getCount() {
		return mCreaterInfos==null?0:mCreaterInfos.size();
	}


	@Override 
	public boolean isViewFromObject(View arg0,Object arg1){
		return arg0==arg1;
	}
	
	
	@Override
	public Object instantiateItem(ViewGroup container,int position){
		final ILayoutPresenter.CreaterInfo createrInfo = mCreaterInfos.get(position);
		final View[] view = {null};
		PresenterManager.getInstance().findPresenter(context,ILayoutPresenterBridge.class).inflate(createrInfo.clazz, new InflateCallBack() {
			
			@Override
			public void onCompleted(LayoutCreater instance) {
				view[0] =instance.getContentView();
			}
		});
		container.addView(view[0]);
		return view[0];
	}
	@Override 
	public void destroyItem(ViewGroup container,int position,Object object){
		container.removeView((View)object);
	}

	private IRequestPresenter getRequester(){
		return PresenterManager.getInstance().findPresenter(context, IRequestPresenterBridge.class);
	}
}
