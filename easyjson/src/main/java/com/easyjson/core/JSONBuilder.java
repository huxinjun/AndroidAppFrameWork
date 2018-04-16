package com.easyjson.core;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.easyjson.handler.IHandler;

/**
 * 将java对象序列化为json字符串
 * @author xinjun
 *
 */
public class JSONBuilder {

	
	public static ArrayList<Class<?>> classes=new ArrayList<Class<?>>();
	
	static{
		addClasses();
	}
	/**
	 * 序列化方法
	 * @param obj 要序列化的类
	 * @return 序列化后的字符串
	 */
	public static String build(Object obj)
	{
		return build(new StringBuilder(),obj,false,false,false,true,true,null);
	}
	/**
	 * 序列化方法
	 * @param obj 要序列化的类
	 * @return 序列化后的字符串
	 */
	public static String build(StringBuilder json,Object obj,boolean inArray,boolean isFirstObjectInArray,boolean isLastObjectInArray,boolean isLastObject,boolean isRootObject,String objectName)
	{
		//看一下是不是数组第一个
		if(inArray)
			if(isFirstObjectInArray)
				json.append("\""+objectName+"\":[");
		//再看一下已不是根对象
		if(isRootObject)
			json.append("{");
		else
			json.append("\""+objectName+"\":{");
		//然后遍历所有的字段，如果是八中基本类型+String就设置字段的值：即    "属性名"：属性值
		Field[] fields = obj.getClass().getDeclaredFields();
		for(int i=0;i<fields.length;i++)
		{
			Field field=fields[i];
			field.setAccessible(true);
			Class<?> fieldTypeClass=field.getType();
			try
			{
				if(classes.contains(field.getType()))
				{
					try {
						addBasicField(json, fieldTypeClass, field.getName(), field.get(obj).toString(),i==fields.length-1);
					} catch (Exception e) {
						addBasicField(json, fieldTypeClass, field.getName(), null,i==fields.length-1);
					}
				}
				//ArrayList字段
				else if(fieldTypeClass.equals(ArrayList.class))
				{
					//获取这个ArrayList运行时的泛型类
					Type type = field.getGenericType();
					//运行时期的泛型类
					Class<?> genericTypeClass=null;
					if(type!=null && type instanceof ParameterizedType)
					{
						genericTypeClass=Class.forName(((ParameterizedType)type).getActualTypeArguments()[0].toString().replace("class ", ""));
					}
					//获取obj的Field字段的值，即ArrayList
					String getMethodName="get"+String.valueOf(field.getName().charAt(0)).toUpperCase()+field.getName().substring(1);
					Method getMethod = obj.getClass().getMethod(getMethodName);
					ArrayList<?> list = (ArrayList<?>) getMethod.invoke(obj);
					//先看一下这个list是不是null
					if(list==null || list.size()==0)
					{
						addNullList(json,field.getName(),i==fields.length);
						continue;
					}
					
					if(classes.contains(genericTypeClass))
					{
						//ArrayList中存储的是普通类型
						for(int j=0;j<list.size();j++)
						{
							try {
								addBasicFieldInArray(json, genericTypeClass, field.getName(), list.get(j).toString().replaceAll("\"", ""),j==0,j==list.size()-1,j==list.size()-1?i==fields.length-1:true);
							} catch (Exception e) {
								addBasicFieldInArray(json, genericTypeClass, field.getName(), null,j==0,j==list.size()-1,j==list.size()-1?i==fields.length-1:true);
							}
						}
					}
					else
					{
						//ArrayList中存储的是对象类型
						for(int j=0;j<list.size();j++)
						{
							//这里面将isLastObject是因为如果是数组的话数组自己会加逗号，置为true意思是外层不会干预数组中的符号
							if(list.get(j)!=null)
								build(json,list.get(j),true,j==0,j==list.size()-1,j==list.size()-1?i==fields.length-1:true,true,field.getName());
							else
								build(json,genericTypeClass.newInstance(),true,j==0,j==list.size()-1,j==list.size()-1?i==fields.length-1:true,true,field.getName());
							
						}
					}
					
				}
				//其他实体类型
				else
				{
					//如果是对象类型就重复调用此方法，直到为基本类型位置
					if(field.get(obj)!=null)
						build(json,field.get(obj),false,false,false,i==fields.length-1,false,field.getName());
					else
						build(json,fieldTypeClass.newInstance(),false,false,false,i==fields.length-1,false,field.getName());
					
				}
			}catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
		json.append("}");
		
		
		if(inArray)
		{
			if(!isLastObjectInArray)
				json.append(",");
			else
				json.append("]");
				
		}
		
		if(!isLastObject)
			json.append(",");
		
		
		return json.toString();
		
	}
	
	/**
	 * 添加一个空集合
	 * @param json
	 * @param name
	 * @param b
	 */
	private static void addNullList(StringBuilder json, String fieldname, boolean isLastInObject) {
		if(isLastInObject)
			json.append("\""+fieldname+"\":[]");
		else
			json.append("\""+fieldname+"\":[],");
	}
	/**
	 * 在需要返回的json中添加一个属性
	 * @param json
	 * @param fieldname
	 * @param fieldvalue
	 * @param isLast 
	 */
	private static void addBasicField(StringBuilder json,Class<?> fieldTypeClass,String fieldname,String fieldvalue, boolean isLast)
	{
		switch(getClassIndex(fieldTypeClass))
		{
			case IHandler.CLASS_STRING:
			case IHandler.CLASS_BOOLEAN:
			case IHandler.CLASS_BOOLEAN_OBJECT:
			case IHandler.CLASS_CHAR:
			case IHandler.CLASS_CHAR_OBJECT:
				//这些值都需要使用双引号括起来
				if(isLast)
					json.append("\""+fieldname+"\":\""+fieldvalue+"\"");
				else
					json.append("\""+fieldname+"\":\""+fieldvalue+"\",");
					
				break;
				
				
				
			case IHandler.CLASS_SHORT:
			case IHandler.CLASS_SHORT_OBJECT:
			case IHandler.CLASS_INT:
			case IHandler.CLASS_INT_OBJECT:
			case IHandler.CLASS_LONG:
			case IHandler.CLASS_LONG_OBJECT:
			case IHandler.CLASS_FLOAT:
			case IHandler.CLASS_FLOAT_OBJECT:
			case IHandler.CLASS_DOUBLE:
			case IHandler.CLASS_DOUBLE_OBJECT:
			case IHandler.CLASS_BYTE:
			case IHandler.CLASS_BYTE_OBJECT:
				//这些值不需要用双引号括起来
				if(isLast)
					json.append("\""+fieldname+"\":"+fieldvalue+"");
				else
					json.append("\""+fieldname+"\":"+fieldvalue+",");
					
				
				break;
				
				
		}
	}
	/**
	 * 在需要返回的json中添加一个属性
	 * @param json
	 * @param fieldname
	 * @param fieldvalue
	 * @param isLastVlue 
	 */
	private static void addBasicFieldInArray(StringBuilder json,Class<?> fieldTypeClass,String fieldname,String fieldvalue,boolean isFirstValue, boolean isLastVlue ,boolean isLastList)
	{
		switch(getClassIndex(fieldTypeClass))
		{
			case IHandler.CLASS_STRING:
			case IHandler.CLASS_BOOLEAN:
			case IHandler.CLASS_BOOLEAN_OBJECT:
			case IHandler.CLASS_CHAR:
			case IHandler.CLASS_CHAR_OBJECT:
				//这些值都需要使用双引号括起来
				if(isFirstValue)
					json.append("\""+fieldname+"\":[\""+fieldvalue+"\",");
				else if(isLastVlue)
					json.append("\""+fieldvalue+"\"]");
				else
					json.append("\""+fieldvalue+"\",");
				if(!isLastList)
					json.append(",");
				break;
				
				
				
			case IHandler.CLASS_SHORT:
			case IHandler.CLASS_SHORT_OBJECT:
			case IHandler.CLASS_INT:
			case IHandler.CLASS_INT_OBJECT:
			case IHandler.CLASS_LONG:
			case IHandler.CLASS_LONG_OBJECT:
			case IHandler.CLASS_FLOAT:
			case IHandler.CLASS_FLOAT_OBJECT:
			case IHandler.CLASS_DOUBLE:
			case IHandler.CLASS_DOUBLE_OBJECT:
			case IHandler.CLASS_BYTE:
			case IHandler.CLASS_BYTE_OBJECT:
				//这些值不需要用双引号括起来
				if(isFirstValue)
					json.append("\""+fieldname+"\":["+fieldvalue+",");
				else if(isLastVlue)
					json.append(fieldvalue+"]");
				else
					json.append(fieldvalue+",");
				if(!isLastList)
					json.append(",");
				break;
			
			
		}
	}
	
	
	private static void addClasses()
	{
		classes.add(String.class);
		classes.add(int.class);
		classes.add(Integer.class);
		classes.add(short.class);
		classes.add(Short.class);
		classes.add(long.class);
		classes.add(Long.class);
		classes.add(float.class);
		classes.add(Float.class);
		classes.add(double.class);
		classes.add(Double.class);
		classes.add(boolean.class);
		classes.add(Boolean.class);
		classes.add(char.class);
		classes.add(Character.class);
		classes.add(byte.class);
		classes.add(Byte.class);
	}
	
	/**
	 * 获取和这个clazz对应的序号
	 * @param clazz
	 * @return
	 */
	private static int getClassIndex(Class<?> clazz)
	{
		return classes.indexOf(clazz);
	}
}
