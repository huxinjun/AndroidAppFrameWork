package com.app;


import java.lang.annotation.Annotation;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.annotation.creater.BindLayoutCreater;
import com.app.presenter.IAnnotationPresenter;
import com.app.presenter.IAnnotationPresenterBridge;
import com.app.presenter.IRequestPresenter;
import com.app.presenter.IRequestPresenterBridge;
import com.app.presenter.PresenterManager;
import com.app.presenter.impl.layout.LayoutCreater;

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


	private IRequestPresenter getRequester(){
		return PresenterManager.getInstance().findPresenter(getActivity(), IRequestPresenterBridge.class);
	}
}
