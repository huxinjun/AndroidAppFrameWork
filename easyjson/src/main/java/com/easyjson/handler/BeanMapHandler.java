package com.easyjson.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.easyjson.core.JSONHashMap;

/**
 * 搜索json对象中某对象的处理器
 * 在Message.createBean()中传入此处理器可以获取json对象中全部某种类型的对象
 * @author xinjun
 *
 * @param <T> 要返回的JavaBean的类型
 */
public class BeanMapHandler<K,V>  extends BaseBeanHandler<Map<K,V>>{

	
	public BeanMapHandler(Class<?> beanClass) {
		super(beanClass);
	}

	@SuppressWarnings({"unchecked" })
	@Override
	public HashMap<K,V> handle(JSONHashMap map)
	{
		
		HashMap<K,V> result=new HashMap<K,V>();
		try
		{
			//要解析首先保证传来的map里面有东西
			if(map.size()==0)
				return null;
			//否则就是对象数组后解析
			for(String key:map.keySet())
			{
				if(getClassIndex(beanClass)!=-1){
					//与key对应的value可能是基本类型
					String string = String.valueOf(map.get(key));
					Object realValue = getRealValue(string);
					result.put((K) key,(V) realValue);
				}else if(beanClass.equals(ArrayList.class)){
					//可能是对象类型
					//TODO
				}else{
					//可能是数组类型
					//TODO
				}
					
				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	private Object getRealValue(String value){
		Class<?> realTypeClass = getRealTypeClass(value);
		if(realTypeClass.equals(int.class))
			return Integer.parseInt(value);
		if(realTypeClass.equals(float.class))
			return Float.parseFloat(value);
		if(realTypeClass.equals(boolean.class))
			return Boolean.parseBoolean(value);
		return null;
	}

}



