package com.app.presenter;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.app.presenter.impl.injector.AdapterViewInjector;
import com.app.presenter.impl.injector.ButtonInjector;
import com.app.presenter.impl.injector.CheckBoxInjector;
import com.app.presenter.impl.injector.ImageViewInjector;
import com.app.presenter.impl.injector.RadioButtonInjector;
import com.app.presenter.impl.injector.TextViewInjector;
import com.app.presenter.impl.injector.ViewPagerInjector;

/**
 * Activity业务类
 * 主要负责:
 * 1.启动一个新的Activity
 * 2.按照Activity类声明的注解绑定布局,实例化初始的Fragment,并切换到相应的视图
 * 3.
 * @author xinjun
 *
 */
public interface IInjectionPresenter extends IPresenter {


	/**
	 * 默认的注入器
	 */
	Map<Class<? extends View>,Class<? extends IInjectionPresenter>> DEFAULT_INJECTOR=new HashMap<Class<? extends View>,Class<? extends IInjectionPresenter>>(){
		{
			put(TextView.class, TextViewInjector.class);
			put(ImageView.class, ImageViewInjector.class);
			put(Button.class, ButtonInjector.class);
			put(CheckBox.class, CheckBoxInjector.class);
			put(RadioButton.class, RadioButtonInjector.class);
			put(AdapterView.class, AdapterViewInjector.class);
			put(ViewPager.class, ViewPagerInjector.class);
		}
	};
	
	
	
	void inject(View target,Object value);

}
