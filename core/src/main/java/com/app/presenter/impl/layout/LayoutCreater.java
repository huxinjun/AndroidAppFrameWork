package com.app.presenter.impl.layout;


import android.content.Context;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AdapterView;

import com.app.annotation.BindFieldName;
import com.app.presenter.IInjectionPresenterBridge;
import com.app.presenter.IRequestPresenter;
import com.app.presenter.PresenterManager;
import com.app.utils.ReflectUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

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
public abstract class LayoutCreater<T>{

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
     * view绑定多个list item data
     */
    public static final int TAG_MULTI_DATA_COUNT = TAG_START_INDEX + 0x10;
    /**
     * 有时候一个布局要绑定多个对象，这个tag确定绑定的是哪个对象的字段
     * view绑定对象中字段时绑定那个索引的对象字段，配合TAG_MULTI_DATA_COUNT使用
     */
    public static final int TAG_MULTI_DATA_INDEX = TAG_START_INDEX + 0x11;
    /**
     * 图片加载需要的参数
     */
    public static final int TAG_IMAGE_OPTION = TAG_START_INDEX + 0x12;

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
     * 这个布局对应的数据(多个)
     */
    private List<T> mContentDatas;

    /**
     * 这个布局在父布局中的索引(-1表示这个布局不处于AdapterView或者其他适配器视图中,是一个单独的布局)
     */
    private int mInParentIndex = -1;

    //----------------------------
    protected LayoutCreater getHeaderCreater(AdapterView view){
        return (LayoutCreater) view.getTag(TAG_LAYOUT_CRETAER_HEADER);
    }
    protected LayoutCreater getFooterCreater(AdapterView view){
        //TODO
        return (LayoutCreater) view.getTag(TAG_LAYOUT_CRETAER_HEADER);
    }


    //-------------------------三个生命周期方法----------------------------------------------

    /**
     * LayoutCreater被创建完成,并解析了类上的注解,也设置了mRequestName表示其关注的请求
     */
    public IRequestPresenter.Option onBuildRequest(IRequestPresenter.ParamPool paramPool) {
        return IRequestPresenter.Option.REPLACE;
    }
    /**
     * LayoutCreater被创建完成,并解析了类上的注解,也设置了mRequestName表示其关注的请求
     */
    public void onViewCreated() {
    }

    /**
     * 数据注入完成
     */
    public abstract void onDataPrepared();


    //-----------------------------------------------------------------------

    public interface DataListener{
        void onDataPrepared(Object data);
    }

    public void addDataListener(DataListener dataListener) {
        this.mDataListeners.add(dataListener);
    }

    /**
     * 有些内嵌的LayoutCreater的字段可能需要父LayoutCreater中的数据，他们在解释注解的时候
     * 会在父LayoutCreater上注册一个DataListener
     */
    private List<DataListener> mDataListeners=new ArrayList<>();
    /**
     * 此方法由命令回调反射调用
     * 目的是不想暴露给外界
     */
    private void dataPrepared() {
        if(mDataListeners.size()>0)
            for(DataListener listener:mDataListeners)
                listener.onDataPrepared(mContentData);

        //通知提供给外部的方法
        onDataPrepared();
        //TODO 在此注入
        injection();

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
        //视图
        View view = null;
        try {
            view = (View) viewField.get(this);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("无法在"+this.getClass().getName()+"中找到"+viewField.getName()+"字段");
        }
        //处理多个绑定的实体数据
        Object data=null;
        if(getContentData()!=null)
            data=getContentData();
        else{
            Object dataIndex = view.getTag(LayoutCreater.TAG_MULTI_DATA_INDEX);
            int index= (int) dataIndex;
            if(index==-1)
                throw new RuntimeException(this.getClass().getName()+"中的"+viewField.getName()+"没有为BindFieldName注解配置index属性，原因："+"当一个布局绑定了多个实体数据时，请务必在使用BindFieldName注解时带上index参数以定位捆绑对象的索引（从0开始）");
            data=getContentData(index);
        }
        //视图映射的实体字段
        Object viewData = data;
        //有可能该field所在的LayoutCreater使用的是普通类型的数据,如：int,string,float,boolean等等
        if(!ReflectUtils.isBasicType(data.getClass()))
            viewData = ReflectUtils.getValueByFieldPath(data,bindFieldName);

        PresenterManager.getInstance().findPresenter(getContext(), IInjectionPresenterBridge.class).inject(view, viewData);
    }

    /**
     * 当数据修改后,代理的回调将会通过反射调用此方法
     *
     * @param bindFieldName 修改的字段名称
     */
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
    public T getContentData(int index) {
        if(mContentDatas==null)
            return mContentData;
        return mContentDatas.get(index);
    }

    public void setContentData(T mContentData) {
        this.mContentData = mContentData;
        dataPrepared();

    }
    public void setContentData(List<T> mContentDatas) {
        this.mContentDatas = mContentDatas;
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
    }

}