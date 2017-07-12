package com.app.annotation.fragment;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 将id加入到fragment事务组
 * @author xinjun
 *
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface FragmentContainer {

	/**
	 * 事务名称
	 * @return
	 */
	String name();
	/**
	 * id所代表的索引
	 * @return
	 */
	int index();

}
