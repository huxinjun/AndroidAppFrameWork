package com.easyjson.handler;
import com.easyjson.core.JSONHashMap;
import com.easyjson.exception.PathInvalidateException;

/**
 * FieldHanlder与FildHandler的抽象父类，提供检查路径格式，抛出异常的方法供子类使用
 * @author xinjun
 *
 * @param <T> 要返回字段的类型
 */
public abstract class BaseFieldHandler extends BaseHandler<String> implements IHandler<String> {

	/**
	 * 要获取的字段的类型
	 */
	protected String path;
	
	public BaseFieldHandler(String path) {
		this.path=path;
	}
	
	/**
	 * 检查路径是否正确
	 * @return
	 */
	protected boolean isPathValidate(String path) {
		
		if(path.endsWith("/"))
			return false;
		return true;
	}
	
	protected void throwExceptionIfNeeded(JSONHashMap map,PathInvalidateException exception) throws PathInvalidateException {
		if(map==null)
			throw exception;
	}
	protected void throwException(PathInvalidateException exception) throws PathInvalidateException {
		throw exception;
	}
}
