package com.app.test;

import com.app.SmartDialog;
import com.app.annotation.BindFieldName;
import com.app.annotation.LayoutDataType;
import com.app.annotation.creater.BindLayoutCreater;
import com.app.annotation.creater.BindView;
import com.app.annotation.request.BindUploadProgress;
import com.app.presenter.impl.LayoutPresenter.LayoutCreater;
import com.app.test.TestDialog.TestCreater;

import android.R;
import android.content.Context;
import android.widget.ImageView;

@BindLayoutCreater(creater=TestCreater.class,requestName = Datas.DATA_GET_CODE)
public class TestDialog extends SmartDialog {

	
	
	public TestDialog(Context context) {
		super(context);
	}

	@LayoutDataType(TestModel.class)
	@BindView(R.layout.activity_list_item)
	public static class TestCreater extends LayoutCreater {
		
		
		@BindUploadProgress(paramName="image",format="已经上传${#.##}%")
		@BindView(R.anim.accelerate_decelerate_interpolator)
		public ImageView iv1;
		
		@BindFieldName("abc")
		@BindView(R.anim.accelerate_decelerate_interpolator)
		public ImageView iv2;
		
		

		@Override
		public void onDataPrepared() {
			// TODO Auto-generated method stub
			
		}
	}

	
}
