package com.app.test;

import android.R;
import android.view.View;

import com.app.SmartActivity;
import com.app.annotation.FormatValue;
import com.app.annotation.Injector;
import com.app.annotation.LayoutResource;
import com.app.annotation.creater.BindLayoutCreater;
import com.app.annotation.fragment.ChangeFragment;
import com.app.annotation.fragment.ChangeFragments;
import com.app.annotation.fragment.Fragment;
import com.app.presenter.impl.LayoutPresenter.LayoutCreater;
import com.app.presenter.impl.injector.TextViewInjector;
import com.app.test.TestFragment.TestCreater;

@BindLayoutCreater(creater=TestCreater.class)
public class TestActivity extends SmartActivity {

	@LayoutResource(R.layout.activity_list_item)
	public static class TestCreater extends LayoutCreater {
		
		@Fragment(clazz=TestFragment.class)
		public static final int ID_content=R.id.button1;

		
		@ChangeFragment(fragment=@Fragment(container=ID_content,clazz=TestFragment.class))
		public static final int ID_tab1=R.id.button1;
		
		@ChangeFragment(fragment=@Fragment(container=ID_content,clazz=TestFragment.class))
		public static final int ID_tab2=R.id.button1;
		
		@FormatValue("$(0)=男,$(1)=女")
		@ChangeFragment(fragment=@Fragment(container=ID_content,clazz=TestFragment.class))
		public static final int ID_tab3=R.id.button1;
		
		@ChangeFragments(fragments={
				@Fragment(container=ID_content,clazz=TestFragment.class),
				@Fragment(container=ID_content,clazz=TestFragment.class)}
				)
		@Injector(TextViewInjector.class)
		@FormatValue("aaa$(value)bbb")
		public static final int ID_tab4=R.id.button1;
		
		
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
