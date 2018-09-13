package com.easyjson.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Properties;

/**
 * 持久化
 * @author xinjun
 *
 */
public class JSONPI {

	
	
	private static Properties prop;
	//-----------------------------------------持久化--------------------------------------------------------------------------------
	/**
	 * 在本地文件中获取JavaBean
	 * @param clazz 要获取的JavaBean的字节码
	 * @param path 本地文件路径
	 * @return
	 */
	public static <T> T readBean(Class<T> clazz,String path)
	{
		return (T) readBean(clazz, new File(path));
	}
	/**
	 * 在本地文件中获取JavaBean
	 * @param clazz 要获取的JavaBean的字节码
	 * @param targetFile 本地文件对象
	 * @return
	 */
	public static <T> T readBean(Class<T> clazz,File targetFile)
	{
		ArrayList<T> result = readBeans(clazz, targetFile);
		return result==null||result.size()==0?null:result.get(0);
	}
	
	
	
	
	/**
	 * 将JavaBean对象写入本地文件中
	 * @param obj
	 * @return
	 */
	public static boolean writeBean(Object obj,String path)
	{
		return writeBean(obj, new File(path));
		
	}
	/**
	 * 将JavaBean对象写入本地文件中
	 * @param obj
	 * @return
	 */
	public static boolean writeBean(Object obj,File targetFile)
	{
		ArrayList<Object> beans=new ArrayList<Object>();
		beans.add(obj);
		return writeBeans(beans, targetFile);

	}
	
	
	
	//-------------------------------------------------------------------------------------------------------------------------
	
	/**
	 * 在本地文件中获取JavaBean
	 * @param clazz 要获取的JavaBean的字节码
	 * @param path 本地文件路径
	 * @return
	 */
	public static <T> ArrayList<T> readBeans(Class<T> clazz,String path)
	{
		return (ArrayList<T>) readBeans(clazz, new File(path));
	}
	/**
	 * 在本地文件中获取JavaBean
	 * @param clazz 要获取的JavaBean的字节码
	 * @param targetFile 本地文件对象
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "resource" })
	public static <T> ArrayList<T> readBeans(Class<T> clazz,File targetFile)
	{
		ArrayList<T> beans=new ArrayList<T>();
		try {
			//获取配置文件中此对象记录数
			int recordCount=readRecordCount(targetFile);
			ObjectInputStream ois=new ObjectInputStream(new FileInputStream(targetFile));
			for(int i=0;i<recordCount;i++)
			{
				T bean=(T) clazz.newInstance();
				bean=(T) ois.readObject();
				beans.add(bean);
			}
			return beans;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	
	


	/**
	 * 将JavaBean对象写入本地文件中
	 * @return
	 */
	public static boolean writeBeans(ArrayList<?> beans,String path)
	{
		return writeBeans(beans, new File(path));
		
	}
	/**
	 * 将JavaBean对象写入本地文件中
	 * @return
	 */
	public static boolean writeBeans(ArrayList<?> beans,File targetFile)
	{
		try {
			ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(targetFile));
			for(Object obj:beans)
			{
				oos.writeObject(obj);
			}
			oos.close();
			writeRecordCount(targetFile, beans.size());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	
	
	
	//-------------------------------------------------------------------------------------------------------------------
	
	
	/**
	 * 向对象持久化文件中写入对象的个数
	 * @param targetFile
	 * @return
	 */
	private static void writeRecordCount(File targetFile,int count) {
		if(prop==null)
			prop = new Properties();
		File propFile=new File(targetFile.getParentFile(),"EasyJson.properties");
		try {
			prop.load(new FileInputStream(propFile));
			prop.put(targetFile.getAbsolutePath(), count);
			prop.store(new FileOutputStream(propFile), targetFile.getName()+"");
		} catch (Exception e) {
			try {
				propFile.createNewFile();
				prop.load(new FileInputStream(propFile));
				prop.put(targetFile.getAbsolutePath(), ""+count);
				prop.store(new FileOutputStream(propFile), targetFile.getName()+"");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	/**
	 * 获取一个对象持久化文件中对象的个数
	 * @param targetFile
	 * @return
	 */
	private static int readRecordCount(File targetFile) {
		if(prop==null)
			prop = new Properties();
		File propFile=new File(targetFile.getParentFile(),"EasyJson.properties");
		try {
			prop.load(new FileInputStream(propFile));
			String property = prop.getProperty(targetFile.getAbsolutePath());
			return Integer.parseInt(property);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}
