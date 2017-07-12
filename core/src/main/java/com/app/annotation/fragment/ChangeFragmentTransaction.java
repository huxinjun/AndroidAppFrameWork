package com.app.annotation.fragment;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.app.SmartFragment;
import com.app.annotation.action.None;

/**
 * 切换一个fragment
 * 配置在成员变量或者@ChangeFragments注解中
 * @author xinjun
 *
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ChangeFragmentTransaction {

	/**
	 * 切换视图时容器的id,不设置此参数时默认为字段的值
	 * @return
	 */
	String name();
	
	Class<? extends SmartFragment>[] fragments();
	
	Class<? extends Annotation> action() default None.class;

}
