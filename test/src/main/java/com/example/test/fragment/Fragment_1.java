package com.example.test.fragment;

import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.SmartFragment;
import com.app.ULog;
import com.app.annotation.BindFieldName;
import com.app.annotation.creater.BindLayoutCreater;
import com.app.annotation.creater.BindView;
import com.app.presenter.IRequestPresenter;
import com.app.presenter.IRequestPresenterBridge;
import com.app.presenter.PresenterManager;
import com.app.presenter.impl.layout.LayoutCreater;
import com.example.test.R;
import com.example.test.global.Urls;
import com.example.test.model.Accounts;

@BindLayoutCreater(creater=Fragment_1.MyCreater.class)
public class Fragment_1 extends SmartFragment {

	@BindView(R.layout.fragment_1)
	public static class MyCreater extends LayoutCreater<Accounts> {
		
		
		@BindFieldName("msg")
		@BindView(R.id.tv_content)
		public TextView tv_content;

		@BindLayoutCreater(creater = LvCreater.class)
		@BindView(R.id.lv)
		@BindFieldName("accounts")
		public AdapterView lv;

		@Override
		public void onInitData() {
			PresenterManager.getInstance().findPresenter(getContext(), IRequestPresenterBridge.class).request(Urls.PATTERN_ACCOUNTS_GET_ALL, null, new IRequestPresenter.DataCallBack() {
				@Override
				public void onDataComming(boolean isCache,Object object) {
					setContentData((Accounts) object);
				}
			});
		}

		@Override
		public void onDataPrepared() {
			Accounts contentData = getContentData();
			contentData.setMsg("你！！！");
			ULog.out("Fragment_1.MyCreater.onDataPrepared:"+contentData);
		}
	}


	@BindView(R.layout.layout_account)
	public static class LvCreater extends LayoutCreater<Accounts.Account> {


		@BindFieldName("userIcon")
		@BindView(R.id.iv_icon)
		public ImageView iv_icon;

		@BindFieldName("name")
		@BindView(R.id.tv_name)
		public TextView tv_name;

		@BindFieldName("date")
		@BindView(R.id.tv_date)
		public TextView tv_date;

		@BindFieldName("paidIn")
		@BindView(R.id.tv_paidin)
		public TextView tv_paidin;

		@BindView(R.id.tv_tag)
		public TextView tv_tag;

		@BindFieldName("description")
		@BindView(R.id.tv_content)
		public TextView tv_content;

		@BindFieldName("addrName")
		@BindView(R.id.tv_addr)
		public TextView tv_addr;

		@BindFieldName("dateDis")
		@BindView(R.id.tv_time)
		public TextView tv_time;

		@BindLayoutCreater(creater=ImgCreater.class)
		@BindFieldName("imgs")
		@BindView(R.id.gv_pics)
		public GridView gv_pics;
		@Override
		public void onDataPrepared() {
			if(getContentData().getIsPrivate())
				tv_tag.setVisibility(View.VISIBLE);
			else
				tv_tag.setVisibility(View.GONE);
		}



		@BindView(R.layout.layout_img)
		public static class ImgCreater extends LayoutCreater<String> {

			@BindFieldName
			@BindView(R.id.iv_icon)
			public ImageView iv_icon;

			@Override
			public void onDataPrepared() {
				ULog.out("ImgCreater.onDataPrepared:"+getContentData());

			}
		}



	}

	
}
