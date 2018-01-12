package com.app.annotation.request;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.app.annotation.Interpreter;
import com.app.presenter.IParserPresenter;
import com.app.presenter.impl.annotation.request.ParserInterpreter;

/**
 * 给一个Request指定数据解析器
 * @author xinjun
 *
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Interpreter(ParserInterpreter.class)
public @interface Parser{

	Class<? extends IParserPresenter> value();
	
}
