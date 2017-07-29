package com.app.annotation.creater;

import com.app.presenter.impl.layout.LayoutCreater;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 声明此注解在创建界面后到数据来临前会显示,而且仅此一次
 * @author xinjun
 *
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface BindLayoutCreaterLoading {

	Class<? extends LayoutCreater> creater();
	String requestName();

}
