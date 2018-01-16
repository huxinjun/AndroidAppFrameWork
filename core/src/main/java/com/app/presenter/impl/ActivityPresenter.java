package com.app.presenter.impl;

import java.io.Serializable;
import java.lang.ref.WeakReference;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;

import com.app.SmartActivity;
import com.app.presenter.IActivityPresenter;
import com.app.presenter.IAnnotationPresenter;
import com.app.presenter.IAnnotationPresenterBridge;
import com.app.presenter.PresenterManager;

public class ActivityPresenter implements IActivityPresenter {

	private Context mContext;

	@Override
	public void setContext(Context context) {
		mContext=context;
	}

	@Override
	public Context getContext() {
		return mContext;
	}

	/**
	 * 记录启动信息
	 * @author xinjun
	 *
	 */
	class ActivityInfo{
		Class<? extends SmartActivity> clazz;
		Bundle bundle;
	}
	
	private ActivityInfo startActivityInfo;
	
	@Override
	public IActivityPresenter startActivity(Class<? extends SmartActivity> clazz) {
		startActivityInfo=new ActivityInfo();
		startActivityInfo.clazz=clazz;
		startActivityInfo.bundle=new Bundle();
		return this;
	}

	@Override
	public IActivityPresenter putParam(String key, Object value) {
		if(startActivityInfo==null)
			throw new RuntimeException("请先调用startActivity(Class<? extends SmartActivity> clazz)");
		if(value==null)
			return this;
		
		if(value instanceof Integer){
			startActivityInfo.bundle.putInt(key, (Integer) value);
			
		}else if(value instanceof Float){
			startActivityInfo.bundle.putFloat(key, (Float) value);
			
		}else if(value instanceof Double){
			startActivityInfo.bundle.putDouble(key, (Double) value);
			
		}else if(value instanceof Long){
			startActivityInfo.bundle.putLong(key, (Long) value);
			
		}else if(value instanceof Character){
			startActivityInfo.bundle.putChar(key, (Character) value);
			
		}else if(value instanceof Short){
			startActivityInfo.bundle.putShort(key, (Short) value);
			
		}else if(value instanceof Parcelable){
			startActivityInfo.bundle.putParcelable(key, (Parcelable) value);
			
		}else if(value instanceof Serializable){
			startActivityInfo.bundle.putSerializable(key, (Serializable) value);
			
		}else if(value instanceof IBinder){
			startActivityInfo.bundle.putBinder(key, (IBinder) value);
			
		}
		return this;
	}

	@Override
	public void go() {
		Intent intent=new Intent(getContext(), startActivityInfo.clazz);
		this.getContext().startActivity(intent, startActivityInfo.bundle);
	}

	
	public IAnnotationPresenter getAnnotaionManager(){
		return PresenterManager.getInstance().findPresenter(getContext(),IAnnotationPresenterBridge.class);
	}



}
