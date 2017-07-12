package com.app.presenter;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * 存储管理
 * @author xinjun
 *
 */
public interface IStoragePresenter extends IPresenter {

	public Map<DIR,String> deffDir=new HashMap<DIR,String>();
	/**
	 * 可选的文件夹类型
	 * @author XINJUN
	 *
	 */
	public enum DIR{
		ENTITY,
		IMAGE,
		VIDEO,
		FILE,
		DOWNLOAD,
		ERROR,
		CUSTOMER1,
		CUSTOMER2,
		CUSTOMER3
	}
	
	/**
	 * 设置应用程序的根目录
	 * @param root
	 */
	public abstract void setRoot(String root);
	
	/**
	 * 设置二级目录
	 * @param type 目录类型
	 * @param dir 目录名
	 */
	public abstract void setDirByType(DIR type,String dir);
	/**
	 * 获取一个缓存目录
	 * @param dirType 类型，可以加DirName注解指定该类型指向的目录名称
	 * @return 目录文件对象
	 */
	public abstract File getCacheDir(DIR dirType);

}
