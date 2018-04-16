package com.easyjson.core;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;
/**
 * JSON数据中的键值封装的对象，数组和对象在树形结构中时JSONHashMap对象，其他基本类型的字段是String对象<br>
 * 1.获取叶子节点的方法getValue(String)。<br>
 * 2.获取数组中的单值getValue(int):解释一下：这个方法获取的是那些JSONArray中包含的是一个个字符串而不是JSONObject或键值对的值，<br>
 * 		例如"prom":[      //享受促销信息<br>
 *		  "促销信息一",<br>
 *		  "促销信息二"<br>
 *		]
 * 3.获取数组对象get(int)<br>
 * 4.获取对象或数组get(String)
 * @author xinjun
 */
public class JSONHashMap extends LinkedHashMap<String, JSONHashMap> {

	private static final long serialVersionUID = 1L;
	
	public void put(String key, String value) {
		try {
			Method m=this.getClass().getMethod("put", Object.class,Object.class);
			m.invoke(this, key,value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取JSON数组中的子元素，子元素可能是JSON对象，可能是JSON数组
	 * @param key
	 * @return
	 */
	public JSONHashMap get(int key) {
		return super.get(""+key);
	}
	/**
	 * 获取一个KEY对应的值，这个KEY只能是叶子元素，对应它的值不能是JSON数组，也不能是JSON对象
	 * @param key 键只能是字符串
	 * @return 对应这个键的值
	 */
	public String getValue(String key)
	{
		try {
			Method m = this.getClass().getMethod("get", Object.class);
			return m.invoke(this, key).toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 获取一个KEY对应的数组中的元素值
	 * @param key 数组中元素的位置
	 * @return 该位置上元素的值
	 */
	public String getValue(int key)
	{
		try {
			Method m = this.getClass().getMethod("get", Object.class);
			return m.invoke(this, ""+key).toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
}
