package com.app.test;

import android.R;
import android.widget.ImageView;

import com.app.SmartActivity;
import com.app.annotation.FormatValue;
import com.app.annotation.Injector;
import com.app.annotation.action.OnClick;
import com.app.annotation.creater.BindLayoutCreater;
import com.app.annotation.creater.BindView;
import com.app.annotation.fragment.Fragment;
import com.app.presenter.impl.LayoutPresenter.LayoutCreater;
import com.app.presenter.impl.injector.TextViewInjector;
import com.app.test.TestFragment.TestCreater;

@BindLayoutCreater(creater=TestCreater.class)
public class TestActivity extends SmartActivity {

	@BindView(R.layout.activity_list_item)
	public static class TestCreater extends LayoutCreater {
		
		@Fragment(clazz=TestFragment.class)
		@BindView(R.anim.accelerate_decelerate_interpolator)
		public ImageView iv1;

		
		@BindView(R.anim.accelerate_decelerate_interpolator)
		public ImageView iv2;
		
		@BindView(R.anim.accelerate_decelerate_interpolator)
		public ImageView iv3;
		
		@FormatValue("$(0)=男,$(1)=女")
		@BindView(R.anim.accelerate_decelerate_interpolator)
		public ImageView iv4;
		
		@Injector(TextViewInjector.class)
		@FormatValue("aaa$(value)bbb")
		@BindView(R.anim.accelerate_decelerate_interpolator)
		@OnClick("test")
		public ImageView iv5;

		@Override
		public void onDataPrepared() {
			// TODO Auto-generated method stub
			
		}
		@OnClick(viewId=R.anim.fade_out)
		public void test(){

		}

	}
	
}
