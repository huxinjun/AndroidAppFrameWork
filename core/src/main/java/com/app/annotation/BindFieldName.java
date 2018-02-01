package com.app.annotation;

import com.app.presenter.impl.annotation.BindFieldNameInterpreter;

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
@Interpreter(BindFieldNameInterpreter.class)
public @interface BindFieldName {

	/**
	 * 申明View字段绑定的实体对象的字段
	 * 如果不配置的话，则代表直接使用LayoutCreater的绑定的数据
	 * 配置时会从LayoutCreater的绑定的数据中查找需要的字段
	 *
	 * ！！！如果视图字段需要注入数据，那么必须配置这个注解（不管有没有value）
	 * @return
	 */
	String value() default "";

	/**
	 * ！！！这个参数必须与BindMultiData同时才能生效，BindMultiData需要配置在父LayoutCreater的AdapterView视图字段上
	 * 当一个View绑定了多个实体时，需要配置index表明是这个view使用多个实体中的哪个
	 * @return
	 */
	int index() default -1;

}
