package com.app;

import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.app.presenter.IDataPresenterBridge;
import com.app.presenter.ILayoutPresenterBridge;
import com.app.presenter.PresenterManager;
import com.app.presenter.IDataPresenter.DataInnerCallBack;
import com.app.presenter.IDataPresenter.RequestDataCommand;
import com.app.presenter.ILayoutPresenter.InflateCallBack;
import com.app.presenter.impl.layout.LayoutCreater;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

public class SmartViewPagerSameTemplaterAdapter extends PagerAdapter {

	/**
	 * 相互引用使用弱引用
	 */
	private ViewPager mAdapterView;
	/**
	 * 适配器包含的所有数据(数据真实类型是代理的)
	 */
	private List<Object> mAllDatas;
	
	/**
	 * 使用的视图创建器模板类型
	 */
	private Class<? extends LayoutCreater> mItemCreaterType;


	private Context context;
	
	@SuppressWarnings("unchecked")
	public SmartViewPagerSameTemplaterAdapter(Context context,ViewPager pagerView) {
		this.context=context;
		mAdapterView=pagerView;
		if(mAdapterView!=null){
			mAllDatas=(List<Object>) mAdapterView.getTag(LayoutCreater.TAG_ITEMS_DATA);
			mItemCreaterType=(Class<? extends LayoutCreater>) mAdapterView.getTag(LayoutCreater.TAG_LAYOUT_CRETAER_ITEM_CLASS);
		}
	}

	@Override
	public int getCount() {
		return mAllDatas==null?0:mAllDatas.size();
	}


	@Override 
	public boolean isViewFromObject(View arg0,Object arg1){
		return arg0==arg1;
	}
	
	
	private LayoutCreater tempCreater;
	@Override
	public Object instantiateItem(ViewGroup container,int position){
		PresenterManager.getInstance().findPresenter(context,ILayoutPresenterBridge.class).inflate(mItemCreaterType, new InflateCallBack() {
			
			@Override
			public void onCompleted(LayoutCreater instance) {
				tempCreater=instance;
				tempCreater.setParentCreater((LayoutCreater) mAdapterView.getTag(LayoutCreater.TAG_LAYOUT_CRETAER_PARENT));
				tempCreater.getContentView().setTag(tempCreater);
			}
		});

		tempCreater.setInParentIndex(position);
		Object dataCount = mAdapterView.getTag(LayoutCreater.TAG_MULTI_DATA_COUNT);
		if(dataCount==null){
			Object o = mAllDatas.get(position);
			tempCreater.setContentData(o);
		}else{
			//一个布局绑定多个对象
			int count= (int) dataCount;
			List<Object> datas=new ArrayList<>();
			for(int i=position*count;i<position*count+count;i++)
				datas.add(mAllDatas.get(i));
			tempCreater.setContentData(datas);
		}

//		Object injectFieldPath = mAdapterView.getTag(LayoutCreater.TAG_INJECTOR_FIELD);
//		String fieldPath=injectFieldPath==null?null:injectFieldPath.toString();
//		//数据
//		ULog.out("sendRequestDataCommand");
//		IDataPresenterBridge dataPresenter = PresenterManager.getInstance().findPresenter(context,IDataPresenterBridge.class);
//		dataPresenter.sendRequestDataCommand(new RequestDataCommand(tempCreater.getRequestName(),fieldPath, new DataInnerCallBack(){
//
//			@Override
//			public void onDataComming(RequestDataCommand command,Object data) {
//				//数据来了,这个数据已经有了,发出的请求数据命令只是为了获取到一个代理的对象而已,此方法会在getView返回之前调用
//				LayoutCreater creater=(LayoutCreater) command.getTag();
//				creater.setContentData(data);
//			}
//
//
//		}).setType(RequestDataCommand.TYPE_LIST_OBJECT).setIndex(position).setTag(tempCreater));
//
		
		
		container.addView(tempCreater.getContentView());
		return tempCreater.getContentView();
	}
	@Override 
	public void destroyItem(ViewGroup container,int position,Object object){
		container.removeView((View)object);
	}
}
