package com.app.presenter.impl;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.lang.ref.WeakReference;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.app.presenter.IMD5Presenter;

public class MD5Presenter implements IMD5Presenter {

	private Context mContext;

	@Override
	public void setContext(Context context) {
		mContext=context;
	}

	@Override
	public Context getContext() {
		return mContext;
	}

	@Override
	public String getMd5(String str) {
		try {

			MessageDigest digester = MessageDigest.getInstance("MD5");
			byte[] bytes = digester.digest(str.getBytes());
			StringBuffer sb = new StringBuffer();
			for (byte b : bytes) {
				int num = b & 0xff;
				sb.append(Integer.toHexString(num).length() < 2 ? "0"
						+ Integer.toHexString(num) : Integer.toHexString(num));
			}
			return sb.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}

	@Override
	public String getMd5(File file) {
		try {

			MessageDigest digester = MessageDigest.getInstance("MD5");

			@SuppressWarnings("resource")
			FileInputStream fis = new FileInputStream(file);
			int length = 0;
			byte[] fbytes = new byte[8096];
			while ((length = fis.read(fbytes)) != -1) {
				digester.update(fbytes, 0, length);
			}
			StringBuffer sb = new StringBuffer();
			byte[] bytes = digester.digest();
			for (byte b : bytes) {
				int num = b & 0xff;
				sb.append(Integer.toHexString(num).length() < 2 ? "0"
						+ Integer.toHexString(num) : Integer.toHexString(num));
			}
			return sb.toString();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

}
