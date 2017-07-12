package com.app.annotation.creater;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.app.annotation.Interpreter;
import com.app.presenter.impl.LayoutPresenter.LayoutCreater;
import com.app.presenter.impl.annotation.BindLayoutCreaterInterpreter;
/**
 * 在类型声明上使用此注解,来指示这个类管理的布局所使用的数据类型
 * @author xinjun
 *
 */
@Interpreter(BindLayoutCreaterInterpreter.class)
@Target({ElementType.FIELD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface BindLayoutCreater {

	Class<? extends LayoutCreater> creater();
	String requestName() default "";

}
