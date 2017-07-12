package com.app.annotation.fragment;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.app.annotation.Interpreter;
import com.app.annotation.action.IAction;
import com.app.presenter.impl.annotation.ChangeFragmentInterpreter;

/**
 * 切换一个fragment
 * 配置在成员变量或者@ChangeFragments注解中
 * @author xinjun
 *
 */
@Interpreter(ChangeFragmentInterpreter.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ChangeFragment {

	Fragment fragment();   
	
	Class<? extends IAction> action() default IAction.class;
	
}
