package com.app.annotation.creater;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.app.annotation.Interpreter;
import com.app.presenter.impl.layout.LayoutCreater;
import com.app.presenter.impl.annotation.BindLayoutCreaterHeaderInterpreter;
/**
 * 在类型声明上使用此注解,来指示这个类管理的布局所使用的数据类型
 * @author xinjun
 *
 */
@Interpreter(BindLayoutCreaterHeaderInterpreter.class)
@Target({ElementType.FIELD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface BindLayoutCreaterHeader {

	Class<? extends LayoutCreater> creater();
	String requestName() default "";

}
