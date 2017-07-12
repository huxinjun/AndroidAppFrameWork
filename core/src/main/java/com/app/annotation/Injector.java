package com.app.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.app.presenter.IInjectionPresenter;
/**
 * 在字段上使用此注解表明数据注入器
 * @author xinjun
 *
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Injector {

	Class<? extends IInjectionPresenter> value();

}
