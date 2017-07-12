package com.app.presenter.impl;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Proxy;
import android.net.Uri;

import com.app.presenter.IActivityPresenterBridge;
import com.app.presenter.INetWorkPresenter;
import com.app.presenter.PresenterManager;

public class NetWorkPresenter implements INetWorkPresenter {
	
	/**
	 * 代理IP
	 */
	private String PROXY_IP;
	/**
	 * 代理端口
	 */
	private int PROXY_PORT;

	@Override
	public int checkNetWork() {
		// 判断手机端利用的通信渠道

		// ①判断WIFI可以连接
		boolean isWIFI = isWIFICon();
		// ②判断MOBILE可以连接
		boolean isMOBILE = isMOBILECon();

		if (isWIFI)
			return NET_TYPE_WIFI;
		// 如果有可以利用的通信渠道，是不是MOBILE
		if (isMOBILE) {
			// 如果是，是否是wap方式
			// 读取APN配置信息，如果发现代理信息非空
			readAPN();// 读取联系人信息
			return NET_TYPE_MOBILE;
		}

		return NET_TYPE_NO_NET;
	}
	
	
	/**
	 * 读取APN配置信息
	 * 
	 * @param context
	 */
	@SuppressWarnings("deprecation")
	private void readAPN() {
		try {
			Uri PREFERRED_APN_URI = Uri
					.parse("content://telephony/carriers/preferapn");// 4.0模拟器屏蔽掉该权限

			ContentResolver resolver = getContext().getContentResolver();
			Cursor cursor = resolver.query(PREFERRED_APN_URI, null, null, null,
					null);// 只有一条
			if (cursor != null && cursor.moveToFirst()) {
				PROXY_IP = cursor.getString(cursor.getColumnIndex("proxy"));
				PROXY_PORT = cursor.getInt(cursor.getColumnIndex("port"));
			}
		} catch (Exception se) {
			PROXY_IP = Proxy.getDefaultHost();
			PROXY_PORT = Proxy.getDefaultPort() == -1 ? 80 : Proxy
					.getDefaultPort();
		}

	}
	
	/**
	 * 判断MOBILE可以连接
	 * 
	 * @param context
	 * @return
	 */
	@SuppressWarnings("deprecation")
	private boolean isMOBILECon() {
		ConnectivityManager manager = (ConnectivityManager) getContext()
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo networkInfo = manager
				.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

		if (networkInfo != null) {
			return networkInfo.isConnected();
		}

		return false;
	}

	/**
	 * 判断WIFI可以连接
	 * 
	 * @param context
	 * @return
	 */
	@SuppressWarnings("deprecation")
	private boolean isWIFICon() {
		ConnectivityManager manager = (ConnectivityManager) getContext()
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo networkInfo = manager
				.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

		if (networkInfo != null) {
			return networkInfo.isConnected();
		}

		return false;
	}

	@Override
	public String getProxyIP() {
		return PROXY_IP;
	}

	@Override
	public int getProxyPort() {
		return PROXY_PORT;
	}

	private Context getContext(){
		return PresenterManager.getInstance().findPresenter(IActivityPresenterBridge.class).getContext();
	}
}
