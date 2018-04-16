package com.easyjson.handler;

import java.util.ArrayList;

import com.easyjson.core.JSONHashMap;

/**
 * 所有处理器的接口
 * @author xinjun
 *
 * @param <T> 处理器处理json对象时需要返回的类型
 */
public interface IHandler<T> {

	/**
	 * 代表String类型
	 */
	public static final int CLASS_STRING=0;
	/**
	 * 代表int类型
	 */
	public static final int CLASS_INT=1;
	/**
	 * 代表Integr类型
	 */
	public static final int CLASS_INT_OBJECT=2;
	/**
	 * 代表short类型
	 */
	public static final int CLASS_SHORT=3;
	/**
	 * 代表Short类型
	 */
	public static final int CLASS_SHORT_OBJECT=4;
	/**
	 * 代表long类型
	 */
	public static final int CLASS_LONG=5;
	/**
	 * 代表Long类型
	 */
	public static final int CLASS_LONG_OBJECT=6;
	/**
	 * 代表folat类型
	 */
	public static final int CLASS_FLOAT=7;
	/**
	 * 代表Float类型
	 */
	public static final int CLASS_FLOAT_OBJECT=8;
	/**
	 * 代表double类型
	 */
	public static final int CLASS_DOUBLE=9;
	/**
	 * 代表Double类型
	 */
	public static final int CLASS_DOUBLE_OBJECT=10;
	/**
	 * 代表boolean类型
	 */
	public static final int CLASS_BOOLEAN=11;
	/**
	 * 代表Boolean类型
	 */
	public static final int CLASS_BOOLEAN_OBJECT=12;
	/**
	 * 代表char类型
	 */
	public static final int CLASS_CHAR=13;
	/**
	 * 代表Char类型
	 */
	public static final int CLASS_CHAR_OBJECT=14;
	/**
	 * 代表byte类型
	 */
	public static final int CLASS_BYTE=15;
	/**
	 * 代表Byte类型
	 */
	public static final int CLASS_BYTE_OBJECT=16;
	
	public static ArrayList<Class<?>> classes=new ArrayList<Class<?>>();
	
	/**
	 * 处理给定的JSONHashMap对象,可能转换为实体对象,也可能只是取基本的值
	 * @param jsonHashMap json树形结构
	 * @return 需要的实体JavaBean类型
	 */
	public T handle(JSONHashMap jsonHashMap);
}
