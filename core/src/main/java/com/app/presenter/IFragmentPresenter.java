package com.app.presenter;

import com.app.SmartFragment;

/**
 * Activity业务类
 * 主要负责:
 * 1.启动一个新的Activity
 * 2.按照Activity类声明的注解绑定布局,实例化初始的Fragment,并切换到相应的视图
 * 3.
 * @author xinjun
 *
 */
public interface IFragmentPresenter extends IPresenter {

	
	/**
	 * 切换fragment是需要的信息
	 * @author xinjun
	 *
	 */
	public static class FragmentInfo{
		public int viewID;
		public Class<? extends SmartFragment> clazz;
		public FragmentInfo(int viewID, Class<? extends SmartFragment> clazz) {
			super();
			this.viewID = viewID;
			this.clazz = clazz;
		}
		
		
	}
	
	public abstract void changeFragment(FragmentInfo... fragments);

}
