package com.app.presenter;

/**
 * 网络请求
 * @author xinjun
 *
 */
public interface INetWorkPresenter extends IPresenter {

	
	/**
	 * 表示没有网络，飞行模式或者关闭了网络链接
	 */
	public static final int NET_TYPE_NO_NET = 1;
	/**
	 * wifi
	 */
	public static final int NET_TYPE_WIFI = 2;
	/**
	 * mobile
	 */
	public static final int NET_TYPE_MOBILE = 3;
	
	
	public int checkNetWork();
	
	public String getProxyIP();
	
	public int getProxyPort();
}
