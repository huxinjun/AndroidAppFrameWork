package com.app.test;

import android.R;
import android.widget.ImageView;

import com.app.SmartActivity;
import com.app.annotation.FormatValue;
import com.app.annotation.Injector;
import com.app.annotation.creater.BindLayoutCreater;
import com.app.annotation.creater.BindView;
import com.app.annotation.fragment.ChangeFragment;
import com.app.annotation.fragment.ChangeFragments;
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

		
		@ChangeFragment(fragment=@Fragment(containerId =123,clazz=TestFragment.class))
		@BindView(R.anim.accelerate_decelerate_interpolator)
		public ImageView iv2;
		
		@ChangeFragment(fragment=@Fragment(containerId =123,clazz=TestFragment.class))
		@BindView(R.anim.accelerate_decelerate_interpolator)
		public ImageView iv3;
		
		@FormatValue("$(0)=男,$(1)=女")
		@ChangeFragment(fragment=@Fragment(containerId =123,clazz=TestFragment.class))
		@BindView(R.anim.accelerate_decelerate_interpolator)
		public ImageView iv4;
		
		@ChangeFragments(fragments={
				@Fragment(containerId =123,clazz=TestFragment.class),
				@Fragment(containerId =123,clazz=TestFragment.class)}
				)
		@Injector(TextViewInjector.class)
		@FormatValue("aaa$(value)bbb")
		@BindView(R.anim.accelerate_decelerate_interpolator)
		public ImageView iv5;
		
		
		@Override
		public void onViewCreated() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onDataPrepared() {
			// TODO Auto-generated method stub
			
		}

	}
	
}
