package com.app.presenter.impl;

import java.io.File;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

import com.app.presenter.IActivityPresenterBridge;
import com.app.presenter.IStoragePresenter;
import com.app.presenter.PresenterManager;

public class StoragePresenter implements IStoragePresenter {

	private String mRootDirName="/";
	
	private String sencondaryPath = null;
	private String externalPath = null;
	private String internalPath = null;
	@Override
	public File getCacheDir(DIR dirType) {
		try {
			String dirName=deffDir.get(dirType);
			String dir=mRootDirName+dirName;
			String extPath = getExtPath();
			if (!TextUtils.isEmpty(extPath)) {
				File myRoot = new File(extPath, dir);
				if (!myRoot.exists())
					myRoot.mkdirs();
				return myRoot;
			} else {
				return new File(getContext().getFilesDir(), dir);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public void setDirByType(DIR type, String dir) {
		deffDir.put(type, dir);
	}
	
	
	private String getExtPath() {
		Map<String, String> map = System.getenv();

		String selectPath = selectPath();
		if (selectPath != null)
			return selectPath;

		Set<String> set = map.keySet();
		Iterator<String> keys = set.iterator();
		while (keys.hasNext()) {
			String key = keys.next();
			String value = map.get(key);
//			MyLog.outInnerLogDetail("pop:"+key + ":" + value);

			if ("SECONDARY_STORAGE".equals(key)) {
				sencondaryPath = value;
			}
			if ("EXTERNAL_STORAGE".equals(key)) {
				externalPath = value;
			}
			if ("INTERNAL_STORAGE".equals(key)) {
				internalPath = value;
			}
		}
		// return selectPath();
		String string = selectPath();
		if(TextUtils.isEmpty(string))
			return Environment.getExternalStorageDirectory().getAbsolutePath();
		return string;
	}

	private String selectPath() {
		if (sencondaryPath != null
				&& !android.os.Build.MODEL.equals("HONOR H30-L01")) {
			File file = new File(sencondaryPath);
			if (file.canRead() && file.canWrite())
				return sencondaryPath;
		}
		if (externalPath != null) {
			File file = new File(externalPath);
			if (file.canRead() && file.canWrite())
				return externalPath;
		}
		if (internalPath != null) {
			File file = new File(internalPath);
			if (file.canRead() && file.canWrite())
				return internalPath;
		}
		return null;
	}
	
	
	
	

	@Override
	public void setRoot(String root) {
		mRootDirName=root;
	}
	
	private Context getContext(){
		return PresenterManager.getInstance().findPresenter(IActivityPresenterBridge.class).getContext();
	}


	

}
