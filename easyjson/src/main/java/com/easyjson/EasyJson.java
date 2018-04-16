package com.easyjson;

import java.io.File;
import java.util.ArrayList;

import com.easyjson.core.JSONBuilder;
import com.easyjson.core.JSONHashMap;
import com.easyjson.core.JSONPI;
import com.easyjson.core.JSONParser;
import com.easyjson.exception.PathInvalidateException;
import com.easyjson.handler.BeanHandler;
import com.easyjson.handler.BeanListHandler;
import com.easyjson.handler.FieldHandler;

/**
 * 对象序列化为json
 * json反序列化为对象的入口类
 * 1.提供将JSON对象转化为实体的方法
 * 2.提供在JSON对象搜寻某写实体JAVABEAN的方法
 * 3.提供获取JSON对象任意节点下的某个字段的方法
 * 4.提供获取JSON对象下除过数组和对象以外的属性集合的方法
 * @author xinjun
 *
 */
public class EasyJson {

	
	/**
	 * 提供将JSON对象转化为实体的方法,传入BeanHandler
	 * 提供在JSON对象搜寻某写实体JAVABEAN的方法,传入BeanListHandler
	 * @param json json字符串
	 * @return 你需要的对象
	 */
	public static <T> T getJavaBean(String json,Class<T> beanClass)
	{
		JSONHashMap map=JSONParser.parse(json);
		if(map==null)
			return null;
		return new BeanHandler<T>(beanClass).handle(map);
	}
	
	public static <T> ArrayList<T> getJavaBeans(String json,Class<T> beanClass)
	{
		return getJavaBeans(json, beanClass);
	}

	/**
	 * 提供获取JSON对象任意节点下的某个字段的方法
	 * 提供获取JSON对象下除过数组和对象以外的属性集合的方法
	 * @param json json字符串
	 * @return 你需要的对象
	 * @throws PathInvalidateException 如果路径不正确就抛出此异常
	 */
	public static String getJavaField(String json,String path)
	{
		JSONHashMap map=JSONParser.parse(json);
		if(map==null)
			return null;
		return new FieldHandler(path).handle(map);
	}
	
	/**
	 * 将一个JavaBean对象序列化为Json
	 * @param obj
	 * @return
	 */
	public static String getJson(Object obj)
	{
		return JSONBuilder.build(obj);
	}
	
	
	
	//-----------------------------------------持久化--------------------------------------------------------------------------------
		/**
		 * 在本地文件中获取JavaBean
		 * @param clazz 要获取的JavaBean的字节码
		 * @param path 本地文件路径
		 * @return
		 */
		public static <T> T readBean(Class<T> clazz,String path)
		{
			return (T) JSONPI.readBean(clazz, path);
		}
		/**
		 * 在本地文件中获取JavaBean
		 * @param clazz 要获取的JavaBean的字节码
		 * @param targetFile 本地文件对象
		 * @return
		 */
		public static <T> T readBean(Class<T> clazz,File targetFile)
		{
			return JSONPI.readBean(clazz, targetFile);
		}
		
		/**
		 * 将JavaBean对象写入本地文件中
		 * @return
		 */
		public static boolean writeBean(Object bean,String path)
		{
			return JSONPI.writeBean(bean, path);
			
		}
		/**
		 * 将JavaBean对象写入本地文件中
		 * @return
		 */
		public static boolean writeBean(Object bean,File targetFile)
		{
			return JSONPI.writeBean(bean, targetFile);

		}
		
		/**
		 * 在本地文件中获取JavaBean
		 * @param clazz 要获取的JavaBean的字节码
		 * @param path 本地文件路径
		 * @return
		 */
		public static <T> ArrayList<T> readBeans(Class<T> clazz,String path)
		{
			return JSONPI.readBeans(clazz, path);
		}
		/**
		 * 在本地文件中获取JavaBean
		 * @param clazz 要获取的JavaBean的字节码
		 * @param targetFile 本地文件对象
		 * @return
		 */
		public static <T> ArrayList<T> readBeans(Class<T> clazz,File targetFile)
		{
			return JSONPI.readBeans(clazz,targetFile);
			
		}
		
		/**
		 * 将JavaBean对象写入本地文件中
		 * @return
		 */
		public static boolean writeBeans(ArrayList<?> beans,String path)
		{
			return JSONPI.writeBeans(beans, path);
			
		}
		/**
		 * 将JavaBean对象写入本地文件中
		 * @return
		 */
		public static boolean writeBeans(ArrayList<?> beans,File targetFile)
		{
			return JSONPI.writeBeans(beans, targetFile);
		}
}
