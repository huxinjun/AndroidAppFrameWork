package com.easyjson.exception;

/**
 * 在json中搜索某个字段或者获取某个对象下的所有普通字段时，传入的路径会抛出这个异常
 * 比如找不到属性，找不到索引，路径格式不正确等等
 * @author xinjun
 *
 */
public class PathInvalidateException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -252655045401979152L;
	public static final int ERROR_TYPE_OVER_PATH=0;
	
	public PathInvalidateException() {
		super("路径格式不正确！");
	}
	public PathInvalidateException(int OverPath) {
		super("找到末尾也没找到你想要的！%>_<%");
	}
	
	public PathInvalidateException(String attrName) {
		super("找不到属性: "+attrName+" ,请仔细核对！");
	}
	public PathInvalidateException(String attrName,int arryIndex) {
		super("属性名为: "+attrName+" 下没有找到索引为"+arryIndex+"的值");
	}
	public PathInvalidateException(int deep,String attrName) {
		super("在路径的第"+deep+"层找不到属性名为: "+attrName+ " 的节点,请仔细核对！");
	}
	public PathInvalidateException(int deep,String attrName,int arryIndex) {
		super("在路径的第"+deep+"层,属性名为: "+attrName+" 的节点下没有找到索引为"+arryIndex+"的元素");
	}
}
