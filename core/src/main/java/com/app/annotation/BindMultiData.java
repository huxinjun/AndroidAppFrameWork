package com.app.annotation;

import com.app.presenter.impl.annotation.BindFieldNameInterpreter;
import com.app.presenter.impl.annotation.BindMultiDataInterpreter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 在类型声明上使用此注解,来指示这个类管理的布局所使用的数据类型
 * @author xinjun
 *
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Interpreter(BindMultiDataInterpreter.class)
public @interface BindMultiData {

	int value();

}
