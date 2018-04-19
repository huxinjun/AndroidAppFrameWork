package com.app.annotation.creater;

import com.app.SmartRecyclerAdapter;
import com.app.annotation.Interpreter;
import com.app.presenter.impl.annotation.BindItemDefinerInterpreter;
import com.app.presenter.impl.annotation.BindLayoutCreaterInterpreter;
import com.app.presenter.impl.layout.LayoutCreater;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 适用于RecyclerView的item布局定义
 * @author xinjun
 *
 */
@Interpreter(BindItemDefinerInterpreter.class)
@Target({ElementType.FIELD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface BindItemDefiner {

	Class<? extends SmartRecyclerAdapter.ItemDefiner> value();

}
