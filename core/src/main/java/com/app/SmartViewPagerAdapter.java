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
				instance.setRequestName(createrInfo.requestName);
				//数据


				IRequestPresenter.ParamPool paramPool= IRequestPresenter.ParamPool.obtain();
				IRequestPresenter.Option option = instance.onBuildRequest(createrInfo.requestName,paramPool);
				IRequestPresenter.RequestInfo info=getRequester().build(instance.getRequestName(), option,paramPool);
				if(info!=null)
					info.mCallBack=new IRequestPresenter.DataCallBack() {
						@Override
						public void onDataComming(Object data) {
							//数据来了,这个数据已经有了,发出的请求数据命令只是为了获取到一个代理的对象而已,此方法会在getView返回之前调用
							instance.setContentData(data);
						}
					};
				getRequester().request(info);

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
