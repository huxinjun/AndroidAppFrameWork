package com.easyjson.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 如果JSON中的对应Object的键和实体中Class名称不同，需要声明这个注解
 * @author xinjun
 *
 */

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface JSONClass {
	String value();
}
