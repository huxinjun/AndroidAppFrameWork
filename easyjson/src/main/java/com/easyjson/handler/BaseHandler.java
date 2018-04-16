package com.easyjson.handler;

/**
 * BaseBeanHandler与BaseFieldHandler的抽象父类，提供添加常量获取元素类型的方法供子类使用
 * @author xinjun
 *
 * @param <T> 要返回的JavaBean对象的类型
 */
public abstract class BaseHandler<T> implements IHandler<T>{

	public BaseHandler() {
		addClasses();
	}
	
	protected static void addClasses()
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
	protected static int getClassIndex(Class<?> clazz)
	{
		return classes.indexOf(clazz);
	}
	/**
	 * 获取JSON属性对应的值的真实类型，创建类型是需要使用
	 * @param value String类型的值
	 * @return 真实类型Class
	 */
	protected Class<?> getRealTypeClass(String value) {
		try{
			Class<?> type=null;
			if(value.contains("."))
			{
				//System.out.println("!"+value);
				Float.parseFloat(value);
				type=float.class;
			}
			else
			{
				//System.out.println("@"+value);
				Integer.parseInt(value);
				type=int.class;
			}
			return type;
		}catch(Exception ex){
			if("true".equals(value)||"false".equals(value))
				return boolean.class;
			else
				return String.class;
			
		}
	}
}
