package com.easyjson.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 如果JSON中的属性和你Bean中的字段名字不同，你的Bean中需要在字段上或者get方法上或者set方法上
 * 声明这个属性，这个属性的值对应的是JSON属性的key
 * @author xinjun
 *
 */

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface JSONField {
	String value();
}
