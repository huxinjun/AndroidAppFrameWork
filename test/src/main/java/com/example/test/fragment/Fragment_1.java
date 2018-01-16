package com.example.test.fragment;

import android.accounts.Account;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.app.SmartFragment;
import com.app.ULog;
import com.app.annotation.BindFieldName;
import com.app.annotation.creater.BindLayoutCreater;
import com.app.annotation.creater.BindView;
import com.app.presenter.impl.layout.LayoutCreater;
import com.example.test.R;
import com.example.test.global.Datas;
import com.example.test.model.Accounts;

@BindLayoutCreater(creater=Fragment_1.MyCreater.class, requestName = Datas.data_account_list)
public class Fragment_1 extends SmartFragment {

	@BindView(R.layout.fragment_1)
	public static class MyCreater extends LayoutCreater<Accounts> {
		
		
		@BindFieldName("msg")
		@BindView(R.id.tv_content)
		public TextView tv_content;

		@BindLayoutCreater(creater = LvCreater.class, requestName = Datas.data_account_list)
		@BindView(R.id.lv)
		@BindFieldName("accounts")
		public AdapterView lv;
		@Override
		public void onDataPrepared() {
			Accounts contentData = getContentData();
			contentData.setMsg("你！！！");
			ULog.out("Fragment_1.MyCreater.onDataPrepared:"+contentData);
		}
	}


	@BindView(R.layout.account_item)
	public static class LvCreater extends LayoutCreater<Account> {


		@BindFieldName("name")
		@BindView(R.id.tv_name)
		public TextView tv_name;

		@BindFieldName("userIcon")
		@BindView(R.id.iv_icon)
		public ImageView iv_icon;

		@Override
		public void onDataPrepared() {

		}
	}

	
}
