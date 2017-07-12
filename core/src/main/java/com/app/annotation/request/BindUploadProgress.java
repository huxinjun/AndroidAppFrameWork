package com.app.annotation.request;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 绑定一个上传文件的参数名,当文件上传时将会把进度注入到控件
 * @author xinjun
 *
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface BindUploadProgress {

	/**
	 * 绑定到哪个请求上?
	 * 没有此属性的话一般是SmartDialog关联的Layout中配置的,忽略配置则是FrameWork中请求时所关联的SmartDialog创建
	 * @return
	 */
	String requestName() default "";
	
	String paramName();
	/**
	 * 格式化字符串
	 * @return
	 */
	String format();
}
