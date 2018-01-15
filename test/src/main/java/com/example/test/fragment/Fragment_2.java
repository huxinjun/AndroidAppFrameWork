package com.example.test.fragment;

import android.widget.TextView;

import com.app.SmartFragment;
import com.app.annotation.BindFieldName;
import com.app.annotation.creater.BindLayoutCreater;
import com.app.annotation.creater.BindView;
import com.app.presenter.impl.layout.LayoutCreater;
import com.example.test.R;
import com.example.test.global.Datas;
import com.example.test.model.Accounts;

@BindLayoutCreater(creater=Fragment_2.MyCreater.class, requestName = Datas.data_account_list)
public class Fragment_2 extends SmartFragment {


	public void initData(){

	}

	@BindView(R.layout.fragment_2)
	public static class MyCreater extends LayoutCreater<Accounts> {


		@BindFieldName("abc")
		@BindView(R.id.tv_content)
		public TextView tv_content;

		@Override
		public void onDataPrepared() {
		}
	}

	
}
