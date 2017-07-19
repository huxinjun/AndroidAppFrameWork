package com.app.annotation.fragment;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.app.SmartFragment;
import com.app.annotation.Interpreter;
import com.app.presenter.impl.annotation.FragmentInterpreter;

/**
 * 在类型声明上使用此注解,来指示这个类管理的布局所使用的数据类型
 * @author xinjun
 *
 */
@Interpreter(FragmentInterpreter.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Fragment {

	int containerId() default 0;
	/**
	 * 要切换的fragment类
	 * @return
	 */
	Class<? extends SmartFragment> clazz();

}
