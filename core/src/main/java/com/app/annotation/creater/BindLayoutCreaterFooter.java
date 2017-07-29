package com.app.annotation.creater;

import com.app.presenter.impl.layout.LayoutCreater;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 在类型声明上使用此注解,来指示这个类管理的布局所使用的数据类型
 * @author xinjun
 *
 */
@Target({ElementType.FIELD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface BindLayoutCreaterFooter {

	Class<? extends LayoutCreater> creater();
	String requestName();

}
