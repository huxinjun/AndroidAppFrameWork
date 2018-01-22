package com.app.annotation;

import com.app.presenter.impl.annotation.BindFieldNameInterpreter;
import com.app.presenter.impl.annotation.BindMultiDataInterpreter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 一个adapter的item绑定多个实体对象，需要在适配器视图上申明此注解
 * 再在item的LayoutCreater的视图字段上使用BindFieldName携带index参数即可
 * 如果需要获取item绑定的实体数据，需要调用getContentData(int index)或者getContentDatas()
 * @author xinjun
 *
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Interpreter(BindMultiDataInterpreter.class)
public @interface BindMultiData {

	int value();

}
