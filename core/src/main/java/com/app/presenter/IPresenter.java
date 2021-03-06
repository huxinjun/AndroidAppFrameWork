package com.app.presenter;


import android.content.Context;
import android.text.TextUtils;

import com.app.ULog;

import java.lang.reflect.Field;
import java.util.List;

/**
 * 所有业务类的基类
 * 未申明任何抽象方法，目的只是为了限定工厂制造的类型
 * @author xinjun
 *
 */
public interface IPresenter {

    /**
     * 设置Context
     * @param context
     */
    void setContext(Context context);

    /**
     * 获取Context
     * @return
     */
	Context getContext();


}
