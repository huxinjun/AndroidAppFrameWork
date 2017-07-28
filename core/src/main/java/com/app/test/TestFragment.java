package com.app.test;

import com.app.SmartFragment;
import com.app.annotation.BindFieldName;
import com.app.annotation.LayoutDataType;
import com.app.annotation.action.OnClick;
import com.app.annotation.creater.BindLayoutCreater;
import com.app.annotation.creater.BindLayoutCreaterHeader;
import com.app.annotation.creater.BindLayoutCreaters;
import com.app.annotation.creater.BindView;
import com.app.annotation.creater.ChangeCreater;
import com.app.annotation.request.BindUploadProgress;
import com.app.presenter.impl.LayoutPresenter.LayoutCreater;
import com.app.test.TestFragment.TestCreater;

import android.R;
import android.widget.ImageView;

@BindLayoutCreater(creater=TestCreater.class, requestName = Datas.DATA_GET_CODE)
public class TestFragment extends SmartFragment {

	
	public void initData(){
		
	}
	
	@LayoutDataType(TestModel.class)
	@BindView(R.layout.activity_list_item)
	public static class TestCreater extends LayoutCreater<TestModel> {
		
		
		@BindFieldName("abc")
		@BindView(R.anim.accelerate_decelerate_interpolator)
		public ImageView iv1;
		
		@BindLayoutCreaterHeader(creater=TestCreater.class,requestName = Datas.DATA_GET_CODE)
		@BindLayoutCreater(creater=TestCreater.class,requestName = Datas.DATA_GET_CODE)
		@BindView(R.anim.accelerate_decelerate_interpolator)
		public ImageView iv2;
		
		@BindLayoutCreaters(creaters={
				@BindLayoutCreater(creater=TestCreater.class,requestName = Datas.DATA_GET_CODE),
				@BindLayoutCreater(creater=TestCreater.class,requestName = Datas.DATA_GET_CODE)
		})
		@BindView(R.anim.accelerate_decelerate_interpolator)
		public ImageView iv3;
		
		@BindUploadProgress(requestName=Datas.DATA_GET_CODE,paramName="image",format="已经上传${#.##}%")
		@BindView(R.anim.accelerate_decelerate_interpolator)
		public ImageView iv4;
		
		@ChangeCreater(action = OnClick.class,creater=@BindLayoutCreater(creater=TestCreater.class,requestName = Datas.DATA_GET_CODE), targetId = 123)
		@BindView(R.anim.accelerate_decelerate_interpolator)
		public ImageView iv5;

		@BindView(R.anim.accelerate_decelerate_interpolator)
		public ImageView iv6;
		
		@Override
		public void onDataPrepared() {
			// TODO Auto-generated method stub
			TestModel contentData = getContentData();
		}
	}

	
}
