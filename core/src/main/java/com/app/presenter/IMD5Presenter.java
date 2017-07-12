package com.app.presenter;

import java.io.File;

/**
 * MD5
 * @author xinjun
 *
 */
public interface IMD5Presenter extends IPresenter {

	public String getMd5(String str);
	
	public String getMd5(File file);

}
