package com.app.test;

import com.app.SmartFragment;
import com.app.annotation.BindFieldName;
import com.app.annotation.LayoutDataType;
import com.app.annotation.LayoutResource;
import com.app.annotation.action.OnClick;
import com.app.annotation.creater.BindLayoutCreater;
import com.app.annotation.creater.BindLayoutCreaterHeader;
import com.app.annotation.creater.BindLayoutCreaters;
import com.app.annotation.creater.ChangeCreater;
import com.app.annotation.request.BindUploadProgress;
import com.app.presenter.impl.LayoutPresenter.LayoutCreater;
import com.app.test.TestFragment.TestCreater;

import android.R;

@BindLayoutCreater(creater=TestCreater.class, requestName = Datas.DATA_GET_CODE)
public class TestFragment extends SmartFragment {

	
	public void initData(){
		
	}
	
	@LayoutDataType(TestModel.class)
	@LayoutResource(R.layout.activity_list_item)
	public static class TestCreater extends LayoutCreater {
		
		
		@BindFieldName("abc")
		public static final int ID_iv_btn=R.id.button1;
		
		@BindLayoutCreaterHeader(creater=TestCreater.class,requestName = Datas.DATA_GET_CODE)
		@BindLayoutCreater(creater=TestCreater.class,requestName = Datas.DATA_GET_CODE)
		public static final int ID_lv_content=R.id.button1;
		
		@BindLayoutCreaters(creaters={
				@BindLayoutCreater(creater=TestCreater.class,requestName = Datas.DATA_GET_CODE),
				@BindLayoutCreater(creater=TestCreater.class,requestName = Datas.DATA_GET_CODE)
		})
		public static final int ID_vp_pager=R.id.button1;
		
		@BindUploadProgress(requestName=Datas.DATA_GET_CODE,paramName="image",format="已经上传${#.##}%")
		public static final int ID_pb=R.id.button1;
		
		@ChangeCreater(action = OnClick.class,creater=@BindLayoutCreater(creater=TestCreater.class,requestName = Datas.DATA_GET_CODE), viewID = ID_lv_content)
		public static final int ID_content=R.id.button1;
		
		public static final int ID_tab1=R.id.button1;
		
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
