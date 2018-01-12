package com.example.test.fragment;

import android.widget.TextView;

import com.app.SmartFragment;
import com.app.annotation.BindFieldName;
import com.app.annotation.LayoutDataType;
import com.app.annotation.creater.BindLayoutCreater;
import com.app.annotation.creater.BindView;
import com.app.presenter.impl.layout.LayoutCreater;
import com.app.test.Datas;
import com.app.test.TestModel;
import com.example.test.R;

@BindLayoutCreater(creater=Fragment_2.MyCreater.class, requestName = Datas.DATA_GET_CODE)
public class Fragment_2 extends SmartFragment {


	public void initData(){

	}

	@LayoutDataType(TestModel.class)
	@BindView(R.layout.fragment_2)
	public static class MyCreater extends LayoutCreater<TestModel> {


		@BindFieldName("abc")
		@BindView(R.id.tv_content)
		public TextView tv_content;

		@Override
		public void onDataPrepared() {
		}
	}

	
}
