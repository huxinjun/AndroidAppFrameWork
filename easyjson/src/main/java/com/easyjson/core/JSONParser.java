package com.easyjson.core;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;


/**
 * 将服务器返回的JSON字符串封装成Message对象
 * 
 *             Message
 *                |
 *                |
 *                |
 *          body:JSONHashMap
 *                |
 *                |
 *           ————————————
 *           |           |
 *     key:String  value:JSONHashMap
 *                   	 |
 *                   	 |
 *                ________________
 *                |				 |
 *       key:String			value:JSONHashMap
 *                   			 .
 *                   			 .
 *                   			 .
 *                   	 	____________
 *                   	   |	  		|
 *                   	key:String	   value:String
 *                   
 * 使用树形的封装方式可以方便的使用get(key).get(key).getValue(key)拿到端点的值
 * 我们已将Message转化为实体Bean的方法嵌入到Message对象中，您可以使用
 * Message.createBeanFromMessage(String json,IHandler handler)方法将服务器返回的
 * Message对象转化为您需要的bean或者ArrayListBean，大多数情况下，你可能不需要
 * 使用一个Message来获取节点的值，这时你需要做的就是编写你的JSON协议，使用CreateJsonFromBean插件将
 * JSON协议转换为JAVABean(需要将你的json文本文件放到你的项目的任意目录下，然后右击选择JSON转换JAVABean选项即可),
 * ，但是，在某些时候可能只需要取出某单个节点的值，或者取出某个对象下的一些普通字段的集合
 * 这时你可以使用Message.getFieldFromMessage传入一个FieldHandler()或者传入FieldListHanlder()
 * 你将会获得想要的数据，没错就是这么简单！
 * 
 *     
 * @author xinjun
 */
public class JSONParser {

	public static JSONHashMap parse(String json)
	{
		return parse(json, new JSONHashMap());
	}
	
	
	public static JSONHashMap parse(String json, JSONHashMap parent)
	{
		try {
			JSONObject root = new JSONObject(json);
			//解析体
			/**
			 * {
			 *	  "response": "topic",
			 *	  " topic ": [
			 *		{
			 *	  		"id": "专题活动ID",
			 *	      	"name": "专题活动名称",
			 *	      	"pic": "图片URL"
			 *	    },
			 *		{
			 *	  		"id": "专题活动ID",
			 *	     	"name": "专题活动名称",
			 *	      	"pic": "图片URL"
			 *	    }
			 *	  ]
			 *	}
			 */
			@SuppressWarnings("rawtypes")
			Iterator i=root.keys();
			while(i.hasNext())
			{
				String name=String.valueOf(i.next());
				String value=root.get(name).toString().trim();
				if(value.length()==0)
					continue;

				if(value.charAt(0)=='['){
					//数组
					JSONArray arr=root.getJSONArray(name);
					JSONHashMap value_obj=new JSONHashMap();
					parent.put(name, value_obj);
					if(value.charAt(1)=='\"'){
						//字符数组
						JSONArray strArr=new JSONArray(value);
						for(int j=0;j<strArr.length();j++)
							value_obj.put(j+"", strArr.get(j).toString());
//						for(int j=0;j<value.split(",").length;j++)
//						{
//							value_obj.put(j+"", value.split(",")[j].replace("[","").replace("]", ""));
//						}

					}else if(value.charAt(1)=='{'){
						//对象数组
						for(int j=0;j<arr.length();j++)
						{
							JSONHashMap child=new JSONHashMap();
							value_obj.put(j+"", child);
							//有时候json数组中可能放的值是null，这种情况直接放个空字符串
							String newValue="";
							try{
								//数组中的元素可能是JSONObject，也可能是基本类型
								newValue=arr.getJSONObject(j).toString();
							}catch(Exception ex){
								ex.printStackTrace();
								//出异常就是基本类型，除过String
							}
							parse(newValue,child);
						}
					}else{
						//其他的基本类型数组
						for(int j=0;j<arr.length();j++)
							value_obj.put(j+"", arr.get(j).toString().replace("\"", ""));
					}

				}else if(value.charAt(0)=='{'){
					//对象
					JSONHashMap child=new JSONHashMap();
					parent.put(name, child);
					parse(value,child);
				}else{
					//普通键值对
					parent.put(name, value);
				}
			}
			
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return parent;
	}
}
