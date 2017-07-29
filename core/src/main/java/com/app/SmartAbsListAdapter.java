package com.app;

import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.List;

import com.app.presenter.IDataPresenter.DataInnerCallBack;
import com.app.presenter.IDataPresenter.RequestDataCommand;
import com.app.presenter.ILayoutPresenter.InflateCallBack;
import com.app.presenter.IDataPresenterBridge;
import com.app.presenter.ILayoutPresenterBridge;
import com.app.presenter.PresenterManager;
import com.app.presenter.impl.layout.LayoutCreater;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;

public class SmartAbsListAdapter extends BaseAdapter {

	/**
	 * 这个Adapter被附加的适配器视图
	 * 当View需要被销毁的时候不会因为View附加的Adapter中对其的引用而无法销毁
	 * 虽然不使用弱引用也能销毁(java回收枚举根节点可以销毁整个断了的引用链)
	 * 但用上更好一点,以防万一
	 */
	private WeakReference<AdapterView<BaseAdapter>> mAdapterView;
	
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
	public SmartAbsListAdapter(Context context,AdapterView<BaseAdapter> parent) {
		this.context=context;
		this.mAdapterView=new WeakReference<AdapterView<BaseAdapter>>(parent);
		if(mAdapterView.get()!=null){
			mAllDatas=(List<Object>) mAdapterView.get().getTag(LayoutCreater.TAG_ITEMS_DATA);
			mItemCreaterType=(Class<? extends LayoutCreater>) mAdapterView.get().getTag(LayoutCreater.TAG_LAYOUT_CRETAER_ITEM_CLASS);
		}
	}
	
	@Override
	public int getCount() {
		return mAllDatas==null?0:mAllDatas.size();
	}

	@Override
	public Object getItem(int position) {
		return mAllDatas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	private LayoutCreater tempCreater;
	@SuppressWarnings("unchecked")
	@Override
	public View getView(int position,  final View convertView, ViewGroup parent) {
		if(convertView==null){
			PresenterManager.getInstance().findPresenter(context,ILayoutPresenterBridge.class).inflate(mItemCreaterType, new InflateCallBack() {
				
				@Override
				public void onCompleted(LayoutCreater instance) {
					tempCreater=instance;
					tempCreater.setParentCreater((LayoutCreater) mAdapterView.get().getTag(LayoutCreater.TAG_LAYOUT_CRETAER_PARENT));
					tempCreater.getContentView().setTag(new WeakReference<LayoutCreater>(tempCreater));
				}
			});
		}else{
			tempCreater = ((WeakReference<LayoutCreater>)convertView.getTag()).get();
		}
		/**
		 * 此操作会触发数据更新
		 */
		tempCreater.setInParentIndex(position);
		
		
		//TODO 下面的逻辑需要转移到LayoutCreater中
		//数据
		IDataPresenterBridge dataPresenter = PresenterManager.getInstance().findPresenter(context,IDataPresenterBridge.class);
		dataPresenter.sendRequestDataCommand(new RequestDataCommand(tempCreater.getRequestName(), tempCreater.getContentDataType(), new DataInnerCallBack(){

			@Override
			public void onDataComming(RequestDataCommand command,Object data) {
				//数据来了,这个数据已经有了,发出的请求数据命令只是为了获取到一个代理的对象而已,此方法会在getView返回之前调用
				LayoutCreater creater=(LayoutCreater) command.getTag();
				creater.setContentData(data);
				try {
					Method method = creater.getClass().getMethod("dataPrepared");
					if(method!=null)
						method.invoke(creater);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			
		}).setType(RequestDataCommand.TYPE_LIST_OBJECT).setIndex(position).setTag(tempCreater));
		
		return tempCreater.getContentView();
	}

}
