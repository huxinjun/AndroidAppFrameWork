package com.app;


import java.lang.annotation.Annotation;
import java.lang.reflect.Method;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.annotation.creater.BindLayoutCreater;
import com.app.presenter.IAnnotationPresenter;
import com.app.presenter.IAnnotationPresenterBridge;
import com.app.presenter.IDataPresenter.DataInnerCallBack;
import com.app.presenter.IDataPresenter.RequestDataCommand;
import com.app.presenter.IDataPresenterBridge;
import com.app.presenter.PresenterManager;
import com.app.presenter.impl.layout.LayoutCreater;
import com.app.test.ULog;

public abstract class SmartFragment extends Fragment {

	
	private LayoutCreater mLayoutCreater;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if(mLayoutCreater!=null)
			return mLayoutCreater.getContentView();
		//根据类声明中的注解创建相应的LayoutCreater
		PresenterManager.getInstance().findPresenter(getContext(), IAnnotationPresenterBridge.class).interpreter(this.getClass(), new IAnnotationPresenter.InterpreterCallBack() {
			@Override
			public void onCompleted(Class<? extends Annotation> anno, Object... results) {
				if (anno == BindLayoutCreater.class)
					mLayoutCreater = (LayoutCreater) results[0];
			}
		});
		
		//数据
		IDataPresenterBridge dataPresenter = PresenterManager.getInstance().findPresenter(getActivity(),IDataPresenterBridge.class);
		//为creater和关联布局下的子creater创建一个请求数据的命令并发送到DataPresenter中,然后静静的等待数据到来
		dataPresenter.sendRequestDataCommand(new RequestDataCommand(mLayoutCreater.getRequestName(), mLayoutCreater.getContentDataType(), new DataInnerCallBack(){

			@Override
			public void onDataComming(RequestDataCommand command,Object data) {
				//数据来了,这个数据已经有了,发出的请求数据命令只是为了获取到一个代理的对象而已,此方法会在getView返回之前调用
				LayoutCreater creater=(LayoutCreater) command.getTag();
				creater.setContentData(data);
				ULog.out("SmartFragment.onDataComming:"+data);
			}
			
//			if(!methodName.startsWith("set"))
//				return;
//			//数据来了,这个数据已经有了,发出的请求数据命令只是为了获取到一个代理的对象而已,此方法会在getView返回之前调用
//			LayoutCreater creater=(LayoutCreater) command.getTag();
//			try {
//				Method method = creater.getClass().getMethod("injection",String.class);
//				method.setAccessible(true);
//				if(method!=null)
//					method.invoke(creater,getFieldNameByMethodName(methodName));
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
			
		}).setType(RequestDataCommand.TYPE_SINGLE_OBJECT).setTag(mLayoutCreater));
		
		return onCreateView(inflater, container, savedInstanceState);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		//在layoutcreater中切换fragment会导致layoutcreater的父链断裂,在fragment创建成功后将链修复
		if(mLayoutCreater.getContentView().getParent()!=null
				&& ((View)mLayoutCreater.getContentView().getParent()).getTag(LayoutCreater.TAG_LAYOUT_CRETAER_PARENT)!=null){
			LayoutCreater parentCreater=(LayoutCreater) ((View)mLayoutCreater.getContentView().getParent()).getTag(LayoutCreater.TAG_LAYOUT_CRETAER_PARENT);
			mLayoutCreater.setParentCreater(parentCreater);
		}
	}
	
	
	
	public int animIn(){return 0;}
	public int animOut(){return 0;}
	public void sendRequestInThisBody(){return;}
}
