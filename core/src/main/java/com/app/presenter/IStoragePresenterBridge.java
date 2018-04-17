package com.app.presenter;

import android.content.Context;

import java.io.File;

import com.app.presenter.impl.StoragePresenter;

/**
 * 存储管理器
 * @author xinjun
 *
 */
public class IStoragePresenterBridge extends IPresenterBridge<IStoragePresenter> implements IStoragePresenter{

	public IStoragePresenterBridge() {
		deffDir.put(DIR.DOWNLOAD, DIR.DOWNLOAD.toString());
		deffDir.put(DIR.ENTITY, DIR.ENTITY.toString());
		deffDir.put(DIR.ERROR, DIR.ERROR.toString());
		deffDir.put(DIR.FILE, DIR.FILE.toString());
		deffDir.put(DIR.IMAGE, DIR.IMAGE.toString());
		deffDir.put(DIR.VIDEO, DIR.VIDEO.toString());
		deffDir.put(DIR.CUSTOMER1, DIR.CUSTOMER1.toString());
		deffDir.put(DIR.CUSTOMER2, DIR.CUSTOMER2.toString());
		deffDir.put(DIR.CUSTOMER3, DIR.CUSTOMER3.toString());
	}


	@Override
	public File getCacheDir(DIR dirType) {
		return mSource.getCacheDir(dirType);
	}

	@Override
	protected IStoragePresenter deffaultSource() {
		return new StoragePresenter();
	}

	@Override
	public void setRoot(String root) {
		mSource.setRoot(root);
	}

	@Override
	public void setDirByType(DIR type, String dir) {
		mSource.setDirByType(type, dir);
	}




	

	
}
