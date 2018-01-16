package com.app.presenter.impl.layout;


import android.content.Context;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.View;

import com.app.annotation.BindFieldName;
import com.app.presenter.IDataPresenter;
import com.app.presenter.IInjectionPresenterBridge;
import com.app.presenter.PresenterManager;

import java.lang.reflect.Field;

/**
 * 根据注解创建视图,根据viewid注解创建视图--->数据,视图--->事件之间的映射关系
 * 充当数据和视图的中间类,数据请求到后注入到View,也会注入到此类中
 *
 * @author xinjun
 * @LayoutCreater 只能配置在Activity, Fragment, 视图ID常量上(配置在一个AdapterView的id常量上时表示Item使用的布局, 配置在一个ViewPager的id常量时表明Item使用的布局, 而且每一个界面都一样)
 * @LayoutCreaters 只能配置在视图ID常量上, 而且只能配置在id对应的view类型是ViewPager时, 表示个个页面使用的布局创建器(每一个Page都不同的情况下使用这个注解)
 * @LayoutResource 只能配置在继承了LayoutCreater的类声明上, 表明这个布局创建器管理的布局
 * @LayoutDataType 只能配置在继承了LayoutCreater的类声明上, 指示这个布局关联的数据实体类型
 * @BindJsonKey 只能配置在类静态常量上, 指示这个(视图id=常量值)所关联的基本类型数据
 */
public abstract class LayoutCreater<T> implements IDataPresenter.DataChangedHandler {

    private Handler mHandler=new Handler();
    /**
     * TAG起始索引,小于这个索引Android系统中的View.setTag(int,Object)将不认为是有效的键值
     */
    private static final int TAG_START_INDEX = (int) Math.pow(2, 25);

    /**
     * ListView专用:header对应的LayoutCreater类型
     */
    public static final int TAG_LAYOUT_CRETAER_HEADER_CLASS = TAG_START_INDEX + 0x1;

    /**
     * ListView专用:header对应的LayoutCreater
     */
    public static final int TAG_LAYOUT_CRETAER_HEADER = TAG_START_INDEX + 0x2;

    /**
     * 子条目关联的LayoutCreater的Class
     */
    public static final int TAG_LAYOUT_CRETAER_ITEM_CLASS = TAG_START_INDEX + 0x3;

    /**
     * 子条目关联的LayoutCreater其使用的唯一数据ID
     */
    public static final int TAG_LAYOUT_CRETAER_ITEM_DATA_ID = TAG_START_INDEX + 0x4;
    /**
     * 子条目使用的数据类型(type=Class<?>)
     */
    public static final int TAG_ITEM_DATA_TYPE = TAG_START_INDEX + 0x5;
    /**
     * 所有子条目使用的数据(type=ArrayList<?>)
     */
    public static final int TAG_ITEMS_DATA = TAG_START_INDEX + 0x6;

    /**
     * 父创建器TAG
     */
    public static final int TAG_LAYOUT_CRETAER_PARENT = TAG_START_INDEX + 0x7;

    /**
     * view使用的数据注入器
     */
    public static final int TAG_INJECTOR_CLASS = TAG_START_INDEX + 0x8;
    /**
     * view数据注入使用的数据字段
     */
    public static final int TAG_INJECTOR_FIELD = TAG_START_INDEX + 0x9;
    /**
     * 图片加载需要的参数
     */
    public static final int TAG_IMAGE_OPTION = TAG_START_INDEX + 0x10;

    private Context mContext;
    /**
     * 父创建器
     * 顶层的LayoutCreater的mParentCreater为null
     */
    private LayoutCreater mParentCreater;
    /**
     * 这个布局关联的数据请求码
     */
    private String mRequestName;

    /**
     * 这个布局对应的视图对象
     */
    private View mContentView;

    /**
     * 这个布局对应的数据类型
     */
    private Class<T> mContentDataType;

    /**
     * 这个布局对应的数据
     */
    private T mContentData;

    /**
     * 这个布局在父布局中的索引(-1表示这个布局不处于AdapterView或者其他适配器视图中,是一个单独的布局)
     */
    private int mInParentIndex = -1;

    //-------------------------三个生命周期方法----------------------------------------------

    /**
     * LayoutCreater被创建完成,并解析了类上的注解,也设置了mRequestName表示其关注的请求
     */
    public void onCreated() {
        init();
    }

    /**
     * 数据注入完成
     */
    public abstract void onDataPrepared();


    //-----------------------------------------------------------------------

    /**
     * 执行初始化操作
     * 初始化数据请求,数据请求状态监听,数据改变监听等
     */
    public void init() {
        //1.注册内部数据请求

        //2.注册数据变化处理器

        //3.注册请求状态监听器(用于loading view注解)
    }

    /**
     * 此方法由命令回调反射调用
     * 目的是不想暴露给外界
     */
    private void dataPrepared() {

        //TODO 在此注入
        injection();

        //通知提供给外部的方法
        onDataPrepared();

    }

    /**
     * 注入
     */
    private void injection() {
        Field[] declaredFields = this.getClass().getDeclaredFields();
        if (declaredFields != null) {
            for (Field field : declaredFields) {
                BindFieldName bindFieldName = field.getAnnotation(BindFieldName.class);
                if (bindFieldName != null)
                    injection(bindFieldName.value(), field);

            }
        }
    }

    /**
     * 内部注入
     *
     * @param bindFieldName 绑定的JAVABEAN字段名称
     * @param viewField     声明Annotation的view对象
     */
    private void injection(String bindFieldName, Field viewField) {
        //视图id
        try {

            //视图映射的实体字段
            Field javaBeanField = getContentData().getClass().getDeclaredField(bindFieldName);
            javaBeanField.setAccessible(true);

            //视图映射的实体字段值
            View view = (View) viewField.get(this);
            Object viewData = javaBeanField.get(getContentData());
            //当beforeInject中将javaBeanField字段的值修改了的话,说明viewData现在已经是旧数据了,不需要再注入了
            if (!viewData.equals(javaBeanField.get(getContentData())))
                return;
            PresenterManager.getInstance().findPresenter(getContext(), IInjectionPresenterBridge.class).inject(view, viewData);
        }catch (NoSuchFieldException e1){
            throw new RuntimeException("在" + this.getClass().getName() + "类的" + viewField.getName() + "字段中配置的BindFieldName上找不到映射的JavaBean字段:" + bindFieldName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 当数据修改后,代理的回调将会通过反射调用此方法
     *
     * @param bindFieldName 修改的字段名称
     */
    @Override
    public void onDataChanged(String bindFieldName) {
        int viewId = 0;
        Field viewIdField = null;
        //数据被修改后代理回调将会调用此方法,但是只传一个bindFieldName(映射的字段名称),即被修改数据的字段名称
        try {
            Field[] declaredFields = this.getClass().getDeclaredFields();
            if (declaredFields != null) {
                for (Field field : declaredFields) {
                    BindFieldName bindFieldNameAnno = field.getAnnotation(BindFieldName.class);
                    if (bindFieldNameAnno != null && bindFieldNameAnno.value().equals(bindFieldName)) {
                        viewId = field.getInt(null);
                        viewIdField = field;
                        break;
                    }

                }
            }
            if (viewId == 0)
                return;

            //视图映射的实体字段
            Field javaBeanField = getContentData().getClass().getDeclaredField(bindFieldName);
            if (javaBeanField == null)
                throw new RuntimeException("在" + this.getClass().getName() + "类的" + viewIdField.getName() + "字段中配置的BindFieldName上找不到映射的JavaBean字段:" + bindFieldName);
            //视图映射的实体字段值
            View view = getContentView().findViewById(viewId);
            Object viewData = javaBeanField.get(getContentData());
            PresenterManager.getInstance().findPresenter(getContext(), IInjectionPresenterBridge.class).inject(view, viewData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 切换fragment
     *
     * @param viewID        fragment所在的id,不限定与当前creater
     * @param fragmentClass fragment类
     */
    public void changeFragment(int viewID, Class<? extends Fragment> fragmentClass) {

    }

    public void changeLayoutCreater(int viewID, Class<? extends LayoutCreater> createrClass) {

    }

    public void changeLayoutCreaterData(int viewID, Class<? extends LayoutCreater> createrClass, String requestName) {

    }


    //----------------------------------------getter and setter-----------------------------------------------------------------------------------------------------------
    public Context getContext() {
        return mContext;
    }

    public void setContext(Context mContext) {
        this.mContext = mContext;
    }

    public View getContentView() {
        return mContentView;
    }

    public void setContentView(View mContentView) {
        this.mContentView = mContentView;
    }

    public String getRequestName() {
        return mRequestName;
    }

    public void setRequestName(String mRequestName) {
        this.mRequestName = mRequestName;
    }

    public Class<T> getContentDataType() {
        return mContentDataType;
    }

    public void setContentDataType(Class<?> mContentDataType) {
        this.mContentDataType = (Class<T>) mContentDataType;
    }

    public T getContentData() {
        return mContentData;
    }

    public void setContentData(T mContentData) {
        this.mContentData = mContentData;
        dataPrepared();
    }

    public LayoutCreater getParentCreater() {
        return mParentCreater;
    }

    public void setParentCreater(LayoutCreater mParentCreater) {
        this.mParentCreater = mParentCreater;
    }

    public int getInParentIndex() {
        return mInParentIndex;
    }

    public void setInParentIndex(int mInParentIndex) {
        if (mInParentIndex == getInParentIndex())
            return;
        this.mInParentIndex = mInParentIndex;

        /**
         * 改变位置后整个布局的数据要更新
         */
        init();
    }

}