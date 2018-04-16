package com.easyjson.handler;

import java.lang.reflect.Field;
import java.util.ArrayList;

import com.easyjson.core.JSONHashMap;

/**
 * 搜索json对象中某对象的处理器
 * 在Message.createBean()中传入此处理器可以获取json对象中全部某种类型的对象
 * @author xinjun
 *
 * @param <T> 要返回的JavaBean的类型
 */
public class BeanListHandler<T> extends BaseBeanHandler<ArrayList<T>> {

	private boolean isSearch=false;
	
	public BeanListHandler<T> setSearch(boolean isSearch) {
		this.isSearch = isSearch;
		return this;
	}
	
	public BeanListHandler(Class<T> beanClass) {
		super(beanClass);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public ArrayList<T> handle(JSONHashMap map)
	{
		//使用BeanListHandler传来的map类似于：{"xxx"="xxx",yyy={"0"={...},"1"={...},"2"={...}}}
		//BeanHandler解析普通对象数组时传来的map类似于：{"0"=true,"1"=false,"2"=true}
		//BeanHandler解析对象数组时传来的map类似于：{"0"={...},"1"={...},"2"={...}}
		
		ArrayList<T> result=new ArrayList<T>();
		try
		{
			//要解析首先保证传来的map里面有东西
			if(map.size()==0)
				return null;
			//其次如果传到这里的是一个对象的话，找到这个对象中的所有ArrayList(以 [{ 开头的)
			//遍历寻找所有数组，在每个数组中寻找目标对象，找到后添加到要返回的result中
			if(!isArrayMap(map))
			{
				//遍历这个对象中的所有键，寻找是ArrayList的属性
				for(String key:map.keySet())
				{
					try
					{
						//这个属性是一个数组
						if(isObjectMap(map,key) || isArrayMap(map.get(key)))
						{
							ArrayList<T> child = new BeanListHandler(beanClass).setSearch(true).handle(map.get(key));
							//合并后续查找出来的T对象到数组中
							if(child!=null && child.size()>0)
							{
								for(int j=0;j<child.size();j++)
								{
									result.add(child.get(j));
								}
							}
						}
							
					}catch(ClassCastException cce)
					{
						//出现这个异常是因为如果是对象的话，对象中可能是数组也可能是普通
						//属性，我们不关心普通属性，捉住错误继续遍历
					}
				}
				return result;
			}
			
			//如果是普通对象数组，首先解析并返回
			if(isBaseTypeArrayMap(map))
			{
				try
				{
					for(int i=0;i<map.size();i++)
					{
						if(isTargetObject(map))
							result.add((T) map.getValue(i));
					}
				}catch(Exception ex1)
				{
					ex1.printStackTrace();
					//如果实体里面声明的ArrayList，而JSON中给的却是一个单值，那么这段代码将产生类型不能转换为ArrayList类型的错误	
					result.add((T) map.getValue(0));
				}
				return result;
			}
			//否则就是对象数组后解析
			for(String key:map.keySet())
			{
				try
				{
					//在看一下这个map的某个键的第一个元素是不是对象，不是对象就跳过
					if(!isObjectArrayMap(map))
						continue;
					//看一下当前map下的某个键的值和实体类匹配不匹配
					if(!isTargetObject(map.get(key)))
					{
						//new BeanListHandler(beanClass).handle(map.get(key));
						//不匹配时看子map批不匹配，如果子元素匹配了直接返回子元素对应的map数组中的元素
						ArrayList<T> child = new BeanListHandler(beanClass).handle(map.get(key));
						//合并后续查找出来的T对象到数组中
						for(int j=0;j<child.size();j++)
						{
							result.add(child.get(j));
						}
						continue;
					}
					//走到这里就是个对象，解析这个对象
					T resultItem=(T) new BeanHandler(beanClass).handle(map.get(key));
					result.add(resultItem);
				}
				catch(ClassCastException cce)
				{
					//如果这了出现了类型转换异常，是这么造成的：
					//解析JSON的时候发现数组中的元素不是对象，而是字符串或者八中基本类型，然后就再数组map中按元素的顺序（字符串数字）为键,元素的值为值，存储到数组map中
					//如果这个数组重视对象的话就不一样了，是对象时先创建了一个子map，这个子map中装的是对象的值，按照顺序（字符串数字为键）,对象类型的map（JSONHashMap）为值，存储到外层的数组中
					//这样一来如果使用数组map.getObject(Object key)取元素时，放的是对象的话没有任何问题，但是如果里面放的是普通属性的话，取出来的将会是一个JSONHashMap
					//但是我们在存储的时候存的却是普通的值，能存进去是因为解析的时候使用了反射将值存进去了，理论上任何值都可以存，但是取出来后却是一个JSONHashMap
					//当上面判断这个外层map是对象map还是普通值map的时候调用了toString()方法，这个方法导致了取出来的JSONHashMap对象无法转换为String对象，从而产生这个异常
					//处理的办法就是捕获这个异常，捕获后我们已经知道这是个普通的map，然后取值就可以了
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 判断一个map是不是对象数组，是对象数组时表现为{"0"={...},"1"={...}}
	 * @param jsonHashMap
	 * @return
	 */
	private boolean isObjectArrayMap(JSONHashMap jsonHashMap) {
		//如果是对象数组的话jsonHashMap中是这样的 ----> 索引（字符串的数字）：JSONHashMap（可能是对象，可能是数组）
		//上面这句话说明，如果数组中是对象的话对应的值是JSONHashMap类型，要取出这个类型只能使用get(Object key)
		//但是如果 索引（字符串的数字）对应的是普通类型或者字符串的话，使用get(Object) 或者 get(int)拿到的是普通类型，没有提供toString方法，所以这个报异常说明是普通类型
		try
		{
			if(isArrayMap(jsonHashMap))
			{
				if(isObjectMap(jsonHashMap,"0"))
					return true;
				return false;
			}
			else
				return false;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return false;
		}
	}
	/**
	 * 判断一个map是不是基本类型数组，是基本类型数组时表现为{"0"=true,"1"=false}
	 * @param jsonHashMap
	 * @return
	 */
	private boolean isBaseTypeArrayMap(JSONHashMap jsonHashMap) {
		//如果是对象数组的话jsonHashMap中是这样的 ----> 索引（字符串的数字）：JSONHashMap（可能是对象，可能是数组）
		//上面这句话说明，如果数组中是对象的话对应的值是JSONHashMap类型，要取出这个类型只能使用get(Object key)
		//但是如果 索引（字符串的数字）对应的是普通类型或者字符串的话，使用get(Object) 或者 get(int)拿到的是普通类型，没有提供toString方法，所以这个报异常说明是普通类型
		try
		{
			if(isArrayMap(jsonHashMap))
			{
				if(isObjectMap(jsonHashMap,"0"))
					return false;
				return true;
			}
			else
				return false;
		}
		catch(Exception ex)
		{
			//产生异常说明jsonHashMap.get(0)拿到的是JSONHashMap（是个对象，不是基本类型）
			ex.printStackTrace();
			return true;
		}
	}
	/**
	 * 判断一个map装的是不是对象
	 * @param jsonHashMap
	 * @return
	 */
	private boolean isObjectMap(JSONHashMap jsonHashMap,String key)
	{
		try {
			if(jsonHashMap.get(key).toString().startsWith("{"))
				return true;
			//如果数组中是String的话，get(0)后调用toString不会产生异常，但String不属于JSON对象，所以返回false
			else
				return false;
		} catch (Exception e) {
			return false;
		}
		
	}

	/**
	 * 判断一个map是不是一个数组，是数组时表现为{"0"={...},"1"={...}}
	 * @param jsonHashMap
	 * @return
	 */
	private boolean isArrayMap(JSONHashMap jsonHashMap) {
		try
		{
			for(String key:jsonHashMap.keySet())
			{
				@SuppressWarnings("unused")
				int i=Integer.parseInt(key);
			}
		}
		catch(Exception ex)
		{
//			ex.printStackTrace();
			return false;
		}
		return true;
	}


	/**
	 * 将拿到的JSONHashMap与一个实体类对比，如果字段一致，就说明这个类是需要返回的实体
	 * @param jsonHashMap json的一个节点
	 * @param key 当前的json属性名称，如果全部字段通过比对，还得确认json中的属性名和当前对象时匹配的
	 * @return 是否是和当前JSONHashMap匹配的实体
	 */
	private boolean isTargetObject(JSONHashMap jsonHashMap) {
		
		//如果当前的beanClass是基本类型，直接返回true
		if(classes.contains(beanClass))
			return true;
		for(String key:jsonHashMap.keySet())
		{
			Field field;
			//在搜索模式下（直接由EasyJson.getBean传来的BeanListHandler时如果发现字段不匹配是不需要提示的）
			//但是在自上向下解析模式下，字段不一致时必须抛出异常终止解析！！
			if(isSearch)
				field = getJSONMappingField(beanClass, key,false);
			else
				field = getJSONMappingField(beanClass, key);
			if(field==null)
				return false;
		}
		return true;
		
	}




}



