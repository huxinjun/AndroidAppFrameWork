package com.app.presenter.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.CompressFormat;

import com.app.ULog;
import com.app.presenter.IImagePresenter;
import com.app.presenter.IImagePresenterBridge;
import com.app.presenter.IMD5Presenter;
import com.app.presenter.IMD5PresenterBridge;
import com.app.presenter.IPersistentPresenter;
import com.app.presenter.IStoragePresenter;
import com.app.presenter.IStoragePresenterBridge;
import com.app.presenter.PresenterManager;
import com.app.presenter.IImagePresenter.Rect;
import com.app.presenter.IStoragePresenter.DIR;
import com.easyjson.EasyJson;

public class PersistentPresenter implements IPersistentPresenter {

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
	public void saveObject(String name, Object target) {
		File cacheDir = getStorage().getCacheDir(DIR.ENTITY);
		if (!cacheDir.exists()) {
			cacheDir.mkdirs();
		}
		EasyJson.writeBean(target, new File(cacheDir,name));
	}

	@Override
	public Object getObject(String name,Class<?> clazz) {
		File cacheFile = new File(getStorage().getCacheDir(DIR.ENTITY),name);
		return EasyJson.readBean(clazz, cacheFile);
	}

	@Override
	public void saveObjects(String name, Object... targets) {
		File cacheDir = getStorage().getCacheDir(DIR.ENTITY);
		if (!cacheDir.exists()) {
			cacheDir.mkdirs();
		}
		List<Object> objs=new ArrayList<Object>();
		for(int i=0;i<targets.length;i++)
			objs.add(targets[i]);
		EasyJson.writeBean(objs, new File(cacheDir,name));
	}

	@Override
	public Object[] getObjects(String name,Class<?> clazz) {
		File cacheFile = new File(getStorage().getCacheDir(DIR.ENTITY),name);
		ArrayList<?> readBeans = EasyJson.readBeans(clazz, cacheFile);
		if(readBeans==null)
			return null;
		Object[] results=new Object[readBeans.size()];
		for(int i=0;i<readBeans.size();i++)
			results[i]=readBeans.get(i);
		return results;
	}

	@Override
	public String saveBitmap(String url, Bitmap target) {
		File cacheDir = getStorage().getCacheDir(DIR.IMAGE);
		if (!cacheDir.exists()) {
			cacheDir.mkdirs();
		}
		// java中文件路径中不能包含斜杠，会认为是目录，android有的机器路径中不能包含冒号，会报java.io.IOException:
		// open failed: EINVAL (Invalid argument)异常
		File cacheFile = new File(cacheDir, getMd5Manager().getMd5(url));
		if (cacheFile.exists()) {
			ULog.out("存储缓存文件:缓存文件已经存在！");
			return cacheFile.getAbsolutePath();
		}
		try {
			String substring = url.substring(0, url.lastIndexOf("?") == -1 ? url.length() : url.lastIndexOf("?"));
			@SuppressWarnings("unused")
			boolean success = false;
			FileOutputStream outputStream = new FileOutputStream(cacheFile);
			if (substring.toLowerCase().endsWith("jpg"))
				success = target.compress(CompressFormat.JPEG, 100, outputStream);
			else if (substring.endsWith("png"))
				success = target.compress(CompressFormat.PNG, 100, outputStream);
			// 如果图片没有后缀
			else
				success = target.compress(CompressFormat.PNG, 100, outputStream);
			ULog.out("存储缓存文件:" + cacheFile.getAbsolutePath() + ",状态：" + success);
			outputStream.close();
			target.recycle();
			return cacheFile.getAbsolutePath();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Bitmap getBitmap(String url,Rect rect) {
		Bitmap bitmap = null;
		
		File cacheDir = getStorage().getCacheDir(DIR.IMAGE);
		// java中文件路径中不能包含斜杠，会认为是目录，android有的机器路径中不能包含冒号，会报java.io.IOException:
		// open failed: EINVAL (Invalid argument)异常
		File cacheFile = new File(cacheDir, getMd5Manager().getMd5(url));
		if (cacheFile.exists() && url != null && !"".equals(url) && !cacheFile.isDirectory()) {
			ULog.out("获取本地图片(:"+url+"):" + cacheFile.getAbsolutePath());
			if (rect==null || (rect.maxWidth <= 0 && rect.maxHeight <= 0))
				bitmap = BitmapFactory.decodeFile(cacheFile.getAbsolutePath());
			else
				bitmap = getImageManager().compressImage(cacheFile.getAbsolutePath(), rect);
			
			if(bitmap==null)
				return null;
		}
		return bitmap;
	}
		
	
	
	
	
	
	
	public IMD5Presenter getMd5Manager() {
		return PresenterManager.getInstance().findPresenter(getContext(),IMD5PresenterBridge.class);

	}
	
	private IStoragePresenter getStorage(){
		return PresenterManager.getInstance().findPresenter(getContext(),IStoragePresenterBridge.class);
	}
	private IImagePresenter getImageManager(){
		return PresenterManager.getInstance().findPresenter(getContext(),IImagePresenterBridge.class);
	}

}
