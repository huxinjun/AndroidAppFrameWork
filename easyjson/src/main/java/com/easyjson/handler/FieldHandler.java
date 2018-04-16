package com.easyjson.handler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.easyjson.core.JSONHashMap;
import com.easyjson.exception.PathInvalidateException;

/**
 * 在json对象中搜索某个属性的处理器
 * 在Message.getField()中传入此处理器可以获取json对象中匹配某个路径的属性值
 * @author xinjun
 *
 * @param <T> 要返回属性的类型
 */
public class FieldHandler extends BaseFieldHandler {

	private JSONHashMap currentMap;
	
	public FieldHandler(String path) {
		super(path);
	}
	@Override
	public String handle(JSONHashMap map){
		
		currentMap=map;
		//解析path,遇到数字就是get(数字)，遇到字符串就是get(字符串)，最后一个将当做属性看待
		if(!isPathValidate(path))
			throw new PathInvalidateException();
		
		//去掉开头的“/”
		if(path.startsWith("/"))
			path=path.substring(1, path.length());
		path=path.replace('.', '/');
		String[] items=path.split("/");
		for(int i=0;i<items.length;i++)
		{
			if(i==items.length-1)
			{
				String result=null;
				//取值
				//如果最后一个路径中带有中括号，即是一个数组也要解析
				Pattern p=Pattern.compile("\\[([0-9]+)\\]$");
				Matcher m = p.matcher(items[i]);
				if(m.find())
				{
					int index=Integer.parseInt(m.group(1));
					try {
						currentMap=currentMap.get(items[i].replace(m.group(0), ""));
						//用户填写的路径有错误
						throwExceptionIfNeeded(currentMap, new PathInvalidateException(items[i].replace(m.group(0), "")));
					} catch (NullPointerException e) {
						throwException(new PathInvalidateException(PathInvalidateException.ERROR_TYPE_OVER_PATH));
					}
					
					try {
						result=currentMap.getValue(index);
						return result;
					} catch (NullPointerException e) {
						//没有找到这个索引的子节点
						throwExceptionIfNeeded(currentMap, new PathInvalidateException(items[i].replace(m.group(0), ""),index));
					}
					
				}
				try {
					result=currentMap.getValue(items[i]);
				} catch (Exception e) {
					//没有找到这个索引的子节点
					throwExceptionIfNeeded(currentMap, new PathInvalidateException(items[i]));
				}
				//没有错误就返回数据
				return result;
			}
			else 
			{
				//取节点，返回JSONHashMap,有可能节点是数字，但也能取到相应的map
				// 这种是普通格式：videoshow/videos/0/rating
				// 这种是数组格式：videoshow/videos[0]/rating
				Pattern p=Pattern.compile("\\[([0-9]+)\\]$");
				Matcher m = p.matcher(items[i]);
				if(m.find())
				{
					int index=Integer.parseInt(m.group(1));
					try {
						currentMap=currentMap.get(items[i].replace(m.group(0), ""));
						//用户填写的路径有错误
						throwExceptionIfNeeded(currentMap, new PathInvalidateException(i+1, items[i].replace(m.group(0), "")));
					} catch (NullPointerException e) {
						throwException(new PathInvalidateException(PathInvalidateException.ERROR_TYPE_OVER_PATH));
					}
					
					//获取节点数组中的某个元素----------------------------------------------------------------------------
					try {
						currentMap=currentMap.get(index);
						throwExceptionIfNeeded(currentMap, new PathInvalidateException(i+1, items[i].replace(m.group(0), ""),index));
					} catch (NullPointerException e) {
						throwException(new PathInvalidateException(PathInvalidateException.ERROR_TYPE_OVER_PATH));
					}
					//没有找到这个索引的子节点
					continue;
				}
				try {
					currentMap=currentMap.get(items[i]);
					throwExceptionIfNeeded(currentMap, new PathInvalidateException(i+1, items[i]));
				} catch (NullPointerException e) {
					throwException(new PathInvalidateException(PathInvalidateException.ERROR_TYPE_OVER_PATH));
				}
			}
			
		}
		return null;
	}
}
