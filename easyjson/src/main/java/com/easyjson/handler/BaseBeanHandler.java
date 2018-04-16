package com.easyjson.handler;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.easyjson.annotation.JSONClass;
import com.easyjson.annotation.JSONField;

/**
 * BeanHandler和BeanListHandler的抽象父类，提供一些反射方法供子类使用
 * @author xinjun
 *
 * @param <T> 代表要返回的对象类型
 */
public abstract class BaseBeanHandler<T> extends BaseHandler<T> implements IHandler<T> {

	protected Class<?> beanClass;
	
	public BaseBeanHandler(Class<?> beanClass) {
		this.beanClass=beanClass;
	}
	
	protected boolean isEmpty(String str){
		return str==null||str.trim().equals("")||str.trim().equals("null")?true:false;
	}
	protected boolean isNotEmpty(String str){
		return !isEmpty(str);
	}
	
	/**
	 * 获取和这个JSON属性对应的类名，依据类上加的注解
	 * @param beanClass
	 * @return
	 */
	protected static String getJSONMappingClass(Class<?> beanClass)
	{
		JSONClass jsonClass = beanClass.getAnnotation(JSONClass.class);
		if(jsonClass!=null)
			return jsonClass.value();
		return null;
	}
	/**
	 * 当找不到字段时是不是要抛出运行时异常，默认是抛出的;
	 * @param beanClass 要查找字段的Class
	 * @param key 字段名（json属性名）
	 * @return
	 */
	protected static<T> Field getJSONMappingField(Class<T> beanClass,String key)
	{
		Field field = getJSONMappingField(beanClass, key,true);
		return field;
	}
	/**
	 * 获取和这个JSON属性对应的的字段
	 * 当找不到字段时是不是要抛出运行时异常，默认是抛出的;
	 * @param beanClass 要查找字段的Class
	 * @param key 字段名（json属性名）
	 * @param isThrowRuntimeException 如果找遍整个类都没有找到相应的字段，这个值就只是此时是否需要抛出运行时异常终止程序
	 * @return
	 */
	protected static<T> Field getJSONMappingField(Class<T> beanClass,String key,boolean isThrowRuntimeException)
	{
		//先找一下这个class中有没有和JSONkey对应的字段，如果有说明JSON字符串中的属性和实体中的属性是对应的
		Field field = null;
		try {
			field = beanClass.getDeclaredField(key);
			if(field!=null)return field;
		} catch (NoSuchFieldException e1) {
			//e1.printStackTrace();
		}
		
		//如果没有对应的字段，那么搜索整个class中看有没有配置了json->key的字段
		//首先获取实体类中所有字段
		Field[] fields = beanClass.getDeclaredFields();
		//获取每个字段的Annotation
		for(Field f:fields)
		{
			JSONField annotation = f.getAnnotation(JSONField.class);
			if(annotation!=null)
			{
				//System.out.println("annotation!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+annotation.value());
				//这个字段是和给定的json属性名对应的字段，返回这个字段名称
				if(annotation.value().equals(key))
					return f;
			}
		}
		//如果没有这个字段，而且也没有那个字段声明了和JSONkey所对应的字段名，那么就再去方法上面找
		Method[] methods = beanClass.getMethods();
		for(Method m:methods)
		{
			JSONField annotation = m.getAnnotation(JSONField.class);
			if(annotation!=null)
			{
				if(m.getName().startsWith("get") || m.getName().startsWith("set"))
				{
					String fieldN=m.getName().replaceAll("get", "").replaceAll("set", "");
					String fieldName=String.valueOf(fieldN.charAt(0)).toLowerCase()+fieldN.substring(1);
					try
					{
						return beanClass.getDeclaredField(fieldName);
					} catch (NoSuchFieldException e) {
						//e.printStackTrace();
					}
				}
			}
		}
		//如果方法上面也没有，再判断这个字段是不是八种普通类型+String,如果是就抛出异常，不是的话有可能是ArrayList，也有可能是自定义类型
		//搜索所有字段的类型，包括ArrayList中的泛型，看这个类型声明的Annotation，如果注解的内容可key相同说明找到了
		//获取每个字段的Annotation
		for(Field f:fields)
		{
			if(!classes.contains(f.getType()))
			{
				//可能是ArrayList，可能是自定义类型
				if(f.getType().equals(ArrayList.class))
				{
					//ArrayList
					//获取这个ArrayList运行时的泛型类
					Type type = f.getGenericType();
					Class<?> genericTypeClass=null;
					if(type!=null && type instanceof ParameterizedType)
					{
						try {
							genericTypeClass=Class.forName(((ParameterizedType)type).getActualTypeArguments()[0].toString().replace("class ", ""));
						} catch (ClassNotFoundException e) {
							//e.printStackTrace();
						}
					}
					String mappingClass=getJSONMappingClass(genericTypeClass);
					if(mappingClass!=null && key.equals(mappingClass))
						return f;
				}
				else
				{
					//自定义类型
					String mappingClass=getJSONMappingClass(f.getType());
					if(mappingClass!=null && key.equals(mappingClass))
						return f;
				}
			}
		}
		if(field==null && isThrowRuntimeException){
			System.out.println("不能再你的实体"+beanClass.getName()+"中找到与"+key+"对应的字段，仔细检查json数据,如果你的实体字段名和json中的属性不一样，你可以更改你的实体中的字段名，或者在实体字段或者get,set方法上添加@JSONField注解即可解决，嘿嘿！！！");
			return null;
			//throw new Error(new RuntimeException("不能再你的实体"+beanClass.getName()+"中找到与"+key+"对应的字段，仔细检查json数据,如果你的实体字段名和json中的属性不一样，你可以更改你的实体中的字段名，或者在实体字段或者get,set方法上添加@JSONField注解即可解决，嘿嘿！！！"));
		}
		return field;
		
	}
	
	/**
	 * 获取运行时期的泛型类
	 */
	protected Class<?> getGenericSuperClass(Class<?> classobj) {
		//获取运行时泛型的类型
		Type type = classobj.getGenericSuperclass();
		//运行时泛型类的类型的Class对象
		Class<?> runTypeClass=null;
		if(type!=null && type instanceof ParameterizedType)
		{
			try {
				runTypeClass=Class.forName(((ParameterizedType)type).getActualTypeArguments()[0].toString().replace("class ", ""));
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return runTypeClass;
	}
}
