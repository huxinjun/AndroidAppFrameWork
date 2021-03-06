package com.app.presenter.impl.layout;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import android.content.Context;
import android.view.View;

import com.app.presenter.IAnnotationPresenter;
import com.app.presenter.IAnnotationPresenterBridge;
import com.app.presenter.ILayoutPresenter;
import com.app.presenter.PresenterManager;

/**
 * 布局管理器
 * 管理加载布局和布局中的视图
 *
 * @author xinjun
 */
public class LayoutPresenter implements ILayoutPresenter {

    private Context mContext;

    @Override
    public void setContext(Context context) {
        mContext = context;
    }

    @Override
    public Context getContext() {
        return mContext;
    }


    @Override
    public void inflate(final Class<? extends LayoutCreater> createrClass, final InflateCallBack callBack) {
        LayoutCreater creater = null;
        try {
            creater = createrClass.newInstance();
            creater.setContext(mContext);
        } catch (Exception e) {
            throw new RuntimeException("不能实例化["+createrClass.getName()+"],请确保类和类的默认构造器可访问,如果是内部类:请声明为static类型!!!");
        }
        final LayoutCreater finalCreater = creater;

        //解释LayoutCreater类上的注解
        getAnnotationPresenter().interpreter(createrClass,null,finalCreater);

        //解释LayoutCreater实例中成员变量上的注解
        Field[] declaredFields = createrClass.getDeclaredFields();
        if (declaredFields != null && declaredFields.length > 0) {
            for (final Field field : declaredFields) {
                field.setAccessible(true);
                try {
                    getAnnotationPresenter().interpreter(field, null, finalCreater, field);
                } catch (java.lang.IllegalArgumentException e1) {
                    throw new RuntimeException("注入异常：请检查"+createrClass.getName()+"中"+field.getName()+"字段的类型是否与布局中的一致");
                } catch (Exception ex){
                    throw new RuntimeException(ex);
                }

            }
        }
        //解释LayoutCreater实例中方法成员上的注解
        Method[] declaredMethods = finalCreater.getClass().getDeclaredMethods();
        if (declaredMethods != null && declaredMethods.length > 0) {
            for (final Method method : declaredMethods) {
                method.setAccessible(true);
                try {
                    getAnnotationPresenter().interpreter(method, null, finalCreater, method);
                } catch (Exception e1) {
                    throw new RuntimeException(e1);
                }

            }
        }
        creater.onViewCreated();
        creater.onInitData();
        callBack.onCompleted(creater);

    }


    /**
     * 寻找指定id所在的布局创建器
     *
     * @param viewID 指定id(在一个view树下这个id必须是唯一的)
     * @return 指定id对应的创建器
     */
    public LayoutCreater findCreaterByViewId(LayoutCreater curr, int viewID) {
        if (curr.getParentCreater() != null)
            return findCreaterByViewId(curr.getParentCreater(), viewID);
        View findViewById = curr.getContentView().findViewById(viewID);
        if (findViewById == null)
            throw new RuntimeException("未发现id");
        LayoutCreater targetParentCreater = (LayoutCreater) findViewById.getTag(LayoutCreater.TAG_LAYOUT_CRETAER_PARENT);
        if (targetParentCreater == null)
            throw new RuntimeException("未发现targetParentCreater");
        return targetParentCreater;
    }


    @Override
    public View findView(LayoutCreater creater, int viewId) {
        return creater.getContentView().findViewById(viewId);
    }

    private IAnnotationPresenter getAnnotationPresenter() {
        return PresenterManager.getInstance().findPresenter(getContext(), IAnnotationPresenterBridge.class);
    }


}
