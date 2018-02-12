package com.app.utils;

import android.text.TextUtils;

import com.app.ULog;

import java.lang.reflect.Field;
import java.util.List;

/**
 * 工具箱
 * Created by Administrator on 2018/1/17.
 */

public class ReflectUtils {

    public static boolean isBasicType(Class type){
        boolean result=false;
        if(type==short.class || type==Short.class)
            result=true;
        if(type==int.class || type==Integer.class)
            result=true;
        if(type==float.class || type==Float.class)
            result=true;
        if(type==double.class || type==Double.class)
            result=true;
        if(type==boolean.class || type==Boolean.class)
            result=true;
        if(type==String.class)
            result=true;
        return result;
    }


    /**
     * 反射获取对象中的list
     * @param obj
     * @param fieldPath
     * @return
     */
    public static List getListByFieldPath(Object obj, String fieldPath){
        Object valueFromObjByFieldPath = getValueByFieldPath(obj, fieldPath);
        if(valueFromObjByFieldPath!=null && valueFromObjByFieldPath instanceof List)
            return (List)valueFromObjByFieldPath;
        return null;
    }

    /**
     * 反射获取对象中的子对象
     * @param obj
     * @param fieldPath
     * @return
     */
    public static Object getValueByFieldPath(Object obj, String fieldPath){
        if(TextUtils.isEmpty(fieldPath))
            return obj;
        String[] fields = fieldPath.split("\\.");
        Object result=obj;
        for(String f:fields)
            result=getObjByFieldName(result,f);
//        ULog.out("查找子数组字段从"+obj.getClass().getSimpleName()+"中寻找的"+fieldPath+"结果："+result);
        return result;
    }

    /**
     * 反射获取对象中的某个字段
     * @param rootObj
     * @param fieldName
     * @return
     */
    private static Object getObjByFieldName(Object rootObj, String fieldName){
        if(rootObj==null || TextUtils.isEmpty(fieldName))
            return null;
        Class<?> rootObjClass = rootObj.getClass();
        Field[] rootObjClassDeclaredFields = rootObjClass.getDeclaredFields();
        for(Field field:rootObjClassDeclaredFields){
            field.setAccessible(true);
            if(field.getName().equals(fieldName))
                try {
                    return field.get(rootObj);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("不能在"+rootObj.getClass().getName()+"中找到"+fieldName+"字段！");
                }
        }
        return null;
    }



    /**
     * 根据字段值在一个class对象中找寻相应的字段
     * @param clazz 目标class
     * @param value 目标class中某个字段(静态)的值
     * @return 值所对应的字段
     */
    public static Field getFieldInClassByStaticFieldValue(Class<?> clazz,String value){

        Field[] fields = clazz.getDeclaredFields();
        for(Field field:fields){
            try {
                field.setAccessible(true);
                Object fvalue = field.get(null);
                if(fvalue.equals(value))
                    return field;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        throw new RuntimeException("没有在"+clazz.getName()+"中找到值为"+value+"的字段!");
    }
}
