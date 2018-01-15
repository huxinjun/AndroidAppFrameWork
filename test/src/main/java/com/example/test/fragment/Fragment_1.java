package com.example.test.fragment;

import android.widget.TextView;

import com.app.SmartFragment;
import com.app.annotation.BindFieldName;
import com.app.annotation.LayoutDataType;
import com.app.annotation.creater.BindLayoutCreater;
import com.app.annotation.creater.BindView;
import com.app.presenter.impl.layout.LayoutCreater;
import com.app.test.TestModel;
import com.example.test.R;
import com.example.test.global.Datas;
import com.example.test.model.Accounts;
import com.example.test.utils.ULog;

@BindLayoutCreater(creater=Fragment_1.MyCreater.class, requestName = Datas.data_account_list)
public class Fragment_1 extends SmartFragment {

	
	public void initData(){
		
	}
	
	@LayoutDataType(Accounts.class)
	@BindView(R.layout.fragment_1)
	public static class MyCreater extends LayoutCreater<Accounts> {
		
		
		@BindFieldName("msg")
		@BindView(R.id.tv_content)
		public TextView tv_content;

		@Override
		public void onDataPrepared() {
			Accounts contentData = getContentData();
			ULog.out("Fragment_1.MyCreater.onDataPrepared:"+contentData);
		}
	}

	
}
