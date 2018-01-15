package com.app;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.app.annotation.creater.BindLayoutCreater;
import com.app.presenter.IAnnotationPresenter;
import com.app.presenter.IAnnotationPresenterBridge;
import com.app.presenter.PresenterManager;
import com.app.presenter.impl.layout.LayoutCreater;

import java.lang.annotation.Annotation;

/**
 * 智能的Activity
 * 1.根据类声明的注解加载布局
 * 2.
 * @author xinjun
 *
 */
public class SmartActivity extends FragmentActivity {

    private LayoutCreater mLayoutCreater;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(mLayoutCreater==null) {
            ULog.out(this);
            //根据类声明中的注解创建相应的LayoutCreater
            PresenterManager.getInstance().findPresenter(this, IAnnotationPresenterBridge.class).interpreter(this.getClass(), new IAnnotationPresenter.InterpreterCallBack() {
                @Override
                public void onCompleted(Class<? extends Annotation> anno, Object... results) {
                    if (anno == BindLayoutCreater.class) {
                        mLayoutCreater = (LayoutCreater) results[0];
                        mLayoutCreater.setContext(SmartActivity.this);
                    }
                }
            });
        }
        setContentView(mLayoutCreater.getContentView());

    }

    protected LayoutCreater getLayoutCreater(){
        return mLayoutCreater;
    }

    public Context getContext(){
        return this;
    }
}
