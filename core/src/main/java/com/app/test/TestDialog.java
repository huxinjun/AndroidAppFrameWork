package com.app.test;

import com.app.SmartDialog;
import com.app.SmartFragment;
import com.app.annotation.BindFieldName;
import com.app.annotation.LayoutDataType;
import com.app.annotation.LayoutResource;
import com.app.annotation.creater.BindLayoutCreater;
import com.app.annotation.creater.BindLayoutCreaters;
import com.app.annotation.request.BindUploadProgress;
import com.app.presenter.impl.LayoutPresenter.LayoutCreater;
import com.app.test.TestDialog.TestCreater;

import android.R;
import android.content.Context;
import android.view.View;

@BindLayoutCreater(creater=TestCreater.class,requestName = Datas.DATA_GET_CODE)
public class TestDialog extends SmartDialog {

	
	
	public TestDialog(Context context) {
		super(context);
	}

	@LayoutDataType(TestModel.class)
	@LayoutResource(R.layout.activity_list_item)
	public static class TestCreater extends LayoutCreater {
		
		
		@BindUploadProgress(paramName="image",format="已经上传${#.##}%")
		public static final int ID_pb=R.id.button1;
		
		@BindFieldName("abc")
		public static final int ID_tv=R.id.button1;
		
		

		@Override
		public void onDataPrepared() {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void onViewCreated() {
			// TODO Auto-generated method stub
			
		}
	}

	
}
