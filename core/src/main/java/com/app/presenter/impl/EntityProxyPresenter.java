package com.app.presenter.impl;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import net.sf.cglib.proxy.CallbackFilter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import com.app.presenter.IEntityProxyPresenter;

public class EntityProxyPresenter implements IEntityProxyPresenter {

	@Override
	public BeanProxyInfo findJavaBeanProxy(BeanProxyInfo root,Object proxy){
		if(root.proxy==proxy)
			return root;
		
		BeanProxyInfo result = null;
		if(root.childs==null)
			return null;
		for(BeanProxyInfo child:root.childs){
			if(child.proxy==proxy){
				result=child;
				break;
			}else{
				if(child.childs!=null)
					result=findJavaBeanProxy(child, proxy);
				if(result!=null)
					break;
			}
		}
		return result;
	}
	
	
	/**
	 * 创建一个对象的代理描述类(组合,树形)
	 * @param bean 对象
	 */
	@Override
	public BeanProxyInfo creatJavaProxy(Class<?> clazz,Object bean){
		
		final BeanProxyInfo beanProxy=new BeanProxyInfo();
		beanProxy.originalClass=clazz;
		beanProxy.original=bean;
		
		if(clazz==ArrayList.class){
			creatListHandler(beanProxy,clazz);
			return beanProxy;
			//查看原型,如果是对象
			
		}else
			createObjectHandler(beanProxy,clazz);
		
		//遍历对象中的所有字段
		Class<? extends Object> beanClass = clazz;
		Field[] declaredFields = beanClass.getDeclaredFields();
		if(declaredFields!=null){
			for(Field field:declaredFields){
				field.setAccessible(true);
				try {
					Object fValue=bean==null?null:field.get(bean);
					int classType = getClassType(field.getType());
					switch (classType) {
					case TYPE_VALUE:
						//普通值
						
						break;
					case TYPE_LIST:
						//先给List字段创建代理描述类listProxy
						BeanProxyInfo listProxy = creatJavaProxy(field.getType(),fValue);
						beanProxy.childs.add(listProxy);
						
						//首先看是否是对象列表
						Class<?> genericType = getGenericType(field);
						int itemClassType = getClassType(genericType);
						if(itemClassType==TYPE_OBJECT){
							List<?> itemList=(List<?>) fValue;
							if(itemList!=null && itemList.size()>0){
								for(Object item:itemList)
									//再给list中的每一个对象创建一个代理描述类并添加到List代理描述类listProxy中
									listProxy.childs.add(creatJavaProxy(genericType,item));
							}
							
						}
						break;
					case TYPE_OBJECT:
						//对象
						beanProxy.childs.add(creatJavaProxy(field.getType(),fValue));
						break;
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
			}
		}
		
		
		
		
		
		
		
		
		
		
		return beanProxy;
	}
	
	public static final int TYPE_VALUE=0;
	public static final int TYPE_OBJECT=1;
	public static final int TYPE_LIST=2;
	
	/**
	 * 获取class的类型
	 * @param clazz
	 * @return
	 */
	private int getClassType(Class<?> clazz){
		int type=0;
		if(clazz==String.class
				|| clazz==Short.class
				|| clazz==short.class
				|| clazz==Integer.class
				|| clazz==int.class
				|| clazz==Long.class
				|| clazz==long.class
				|| clazz==Double.class
				|| clazz==double.class
				|| clazz==Float.class
				|| clazz==float.class
				|| clazz==Character.class
				|| clazz==char.class
				|| clazz==Byte.class
				|| clazz==byte.class){
			//普通值
			type=TYPE_VALUE;
		}
		else if(clazz == ArrayList.class){
			//LIST
			type=TYPE_LIST;
		}else{
			//对象
			type=TYPE_OBJECT;
		}
		return type;
	}
	
	/**
	 * 获取字段在运行时的泛型类型
	 * @param field
	 * @return
	 */
	private Class<?> getGenericType(Field field){
		Type type = field.getGenericType();
		Class<?> genericTypeClass=null;
		if(type!=null && type instanceof ParameterizedType)
		{
			try {
				genericTypeClass=Class.forName(((ParameterizedType)type).getActualTypeArguments()[0].toString().replace("class ", ""));
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return genericTypeClass;
	}
	
	
	/**
	 * 给一个list创建代理和方法拦截回调
	 * @param proxy list代理描述类
	 * @param clazz 源对象类型
	 */
	private void creatListHandler(final BeanProxyInfo proxy, Class<?> clazz){
		Enhancer enhancer=new Enhancer();
		enhancer.setSuperclass(clazz);
		enhancer.setInterfaces(new Class[]{Serializable.class});
		enhancer.setCallbacks(proxy.handlers);
		proxy.setObtainHandler(new MethodInterceptor() {
			
			public Object intercept(Object arg0, Method arg1, Object[] arg2,
					MethodProxy arg3) throws Throwable {
				if(arg1.getName().equals("toString"))
					return "cglib-proxy:"+arg1.invoke(proxy.original, arg2);
				
				
				//当遇见get方法时,首先执行原对象的get方法,然后看其源对象的get方法的返回值是什么类型
				//拦截ArrayList:beans中获取数据的get(int)方法,其他的add(Object),addAll(Collection),remove(int),clear()等等方法是修改数据的方法)
				
				if(arg1.getName().equals("get")){
					try {
						Object originalObject = arg1.invoke(proxy.original, arg2);
						if(originalObject==null)
							return null;
						int classType = getClassType(originalObject.getClass());
						if(classType!=TYPE_VALUE){
							for(BeanProxyInfo child:proxy.childs){
								if(child.original.equals(originalObject))
									return child.proxy;
							}
						}
					} catch (Exception e) {
						//list为空,或者索引出错
						e.printStackTrace();
						return null;
					}
				}
				return arg1.invoke(proxy.original, arg2);
			}
		});
		enhancer.setCallbackFilter(new CallbackFilter() {
			
			public int accept(Method arg0) {
				if(arg0.getName().startsWith("get") || arg0.getName().equals("toString"))
					return 0;
				return 1;
			}
		});
		proxy.proxy=enhancer.create();
	}
	
	/**
	 * 给一个对象创建代理和方法拦截回调
	 * @param proxy 对象代理描述类
	 * @param clazz 源对象类型
	 */
	private void createObjectHandler(final BeanProxyInfo proxy,Class<?> beanClass){
		Enhancer enhancer=new Enhancer();
		enhancer.setSuperclass(beanClass);
		enhancer.setInterfaces(new Class[]{Serializable.class});
		enhancer.setCallbacks(proxy.handlers);
		proxy.setObtainHandler(new MethodInterceptor() {
			
			public Object intercept(Object arg0, Method arg1, Object[] arg2,
					MethodProxy arg3) throws Throwable {
				if(arg1.getName().equals("toString"))
					return "cglib-proxy:"+arg1.invoke(proxy.original, arg2);
				
				//当遇见get方法时,首先执行原对象的get方法,然后看其源对象的get方法的返回值是什么类型
				//拦截getXXX方法,如果getXXX拿到的是对象类型就在proxy的childs中找这个对象的代理并返回
				if(arg1.getName().startsWith("get")){
					Object originalObject = arg1.invoke(proxy.original, arg2);
					
					
					if(originalObject==null){
						//如果源对象是null,创建源对象,并调用代理的set方法,最后返回代理,解决使用代理时出现的NullPointException问题
						String fieldName = getFieldNameByMethodName(arg1.getName());
						Field field = proxy.original.getClass().getDeclaredField(fieldName);
						//新的源对象(可能是基本类型)
						Object newInstance = field.getType().newInstance();
						originalObject=newInstance;
						
						String setMethodName = getSetMethodNameByFieldName(fieldName);
						//源对象的set方法
						Method setMethod = arg0.getClass().getDeclaredMethod(setMethodName, field.getType());
						
						//调用代理对象的set方法,目的是修改相应的original对象
						setMethod.invoke(arg0, newInstance);
						
						
					}
					int classType = getClassType(originalObject.getClass());
					if(classType!=TYPE_VALUE){
						for(BeanProxyInfo child:proxy.childs){
							if(child.original.equals(originalObject))
								return child.proxy;
						}
					}
				}
				return arg1.invoke(proxy.original, arg2);
			}
		});
		//set方法拦截
		proxy.addNotifyHandler(new MethodInterceptor() {
			
			public Object intercept(Object arg0, Method arg1, Object[] arg2,
					MethodProxy arg3) throws Throwable {
				if(arg1.getName().startsWith("set")){
					String fieldName = getFieldNameByMethodName(arg1.getName());
					Field field = proxy.original.getClass().getDeclaredField(fieldName);
					Object fieldValue=arg2[0];
					if(fieldValue==null)
						return null;
					
					int classType = getClassType(fieldValue.getClass());
					if(classType!=TYPE_VALUE){
						//TODO 找到该字段对应的代理描述类,并填充其源对象
						for(BeanProxyInfo proxyInfo:proxy.childs)
							if(proxyInfo.originalClass==field.getType()){
								proxyInfo.original=fieldValue;
								break;
							}
					}
				}
				//调用源对象的set方法将新数据添加进去
				return arg1.invoke(proxy.original, arg2);
			}
		});
		enhancer.setCallbackFilter(new CallbackFilter() {
			
			public int accept(Method arg0) {
				if(arg0.getName().startsWith("get") || arg0.getName().equals("toString"))
					return 0;
				return 1;
			}
		});
		proxy.proxy=enhancer.create();
	}
	
	/**
	 * 根据get或者set方法的名称获取相应的字段名称
	 * @param methodName 方法名称
	 * @return 字段名称
	 */
	private String getFieldNameByMethodName(String methodName){
		return String.valueOf(methodName.charAt(3)).toLowerCase()+methodName.substring(4);
	}
	
	/**
	 * 根据一个字段名称获取一个set方法名称
	 * @param fieldName 字段名
	 * @return set方法名称
	 */
	private String getSetMethodNameByFieldName(String fieldName){
		return "set"+String.valueOf(fieldName.charAt(0)).toUpperCase()+fieldName.substring(1);
	}
	

}
