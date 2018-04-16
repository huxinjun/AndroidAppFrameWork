package com.easyjson.handler;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

import com.easyjson.core.JSONHashMap;


/**
 * 默认的json转换JavaBean的处理器
 * 在Message.createBean()方法中传入此处理器可以获得一个JavaBean对象
 *
 * @param <T> 要返回的JavaBean的类型
 * @author xinjun
 */
public class BeanHandler<T> extends BaseBeanHandler<T> {

    public BeanHandler(Class<T> beanClass) {
        super(beanClass);
    }

    //T代表用户传过来的实体类型
    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public T handle(JSONHashMap map) {
        //Class<?> beanClass=null;
        T result = null;

        //拿到实体类型
            /*beanClass=null;
			Type beanClassType = this.getClass().getGenericSuperclass();
			if(beanClassType!=null && beanClassType instanceof ParameterizedType)
			{
				beanClass=Class.forName(((ParameterizedType)beanClassType).getActualTypeArguments()[0].toString().replace("class ", ""));
			}
			//创建实体对象*/
        try {
            result = (T) beanClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        /**
         * 遍历JSONHashMap的所有key，拿出里面的值，如果
         * 值继承自String，那么这是一个字段，实例化target后调用其中的set方法将值填充到字段
         * 值继承自JSONHashMap，说明这个字段对应的值是一个对象，获取这个字段对应的class和这个key对应的value(JSONHashMap),重复此方法，
         * 即可循环将每一个字段的值都设置
         *
         */
        for (String key : map.keySet()) {
            try {
                Field field = getJSONMappingField(beanClass, key);
                if (field == null)
                    continue;
//				System.out.println("和"+key+"对应的字段名称是:"+field.getName());
                field.setAccessible(true);
                Class<?> fieldTypeClass = field.getType();
//				System.out.println(fieldTypeClass);
                //普通字段
                switch (getClassIndex(fieldTypeClass)) {
                    case CLASS_STRING:
                        Method m = beanClass.getMethod("set" + String.valueOf(field.getName().charAt(0)).toUpperCase() + field.getName().substring(1), fieldTypeClass);
                        m.invoke(result, map.getValue(key) != null && !map.getValue(key).toString().equals("null") ? map.getValue(key) : "");
                        continue;
                    case CLASS_SHORT:
                    case CLASS_SHORT_OBJECT:
                        field.setShort(result, Short.parseShort(map.getValue(key) != null && isNotEmpty(map.getValue(key)) ? map.getValue(key) : "0"));
                        continue;
                    case CLASS_INT:
                    case CLASS_INT_OBJECT:
                        field.setInt(result, Integer.parseInt(map.getValue(key) != null && isNotEmpty(map.getValue(key)) ? map.getValue(key) : "0"));
                        continue;
                    case CLASS_LONG:
                    case CLASS_LONG_OBJECT:
                        field.setLong(result, Long.parseLong(map.getValue(key) != null && isNotEmpty(map.getValue(key)) ? map.getValue(key) : "0"));
                        continue;
                    case CLASS_FLOAT:
                    case CLASS_FLOAT_OBJECT:
                        field.setFloat(result, Float.parseFloat(map.getValue(key) != null && isNotEmpty(map.getValue(key)) ? map.getValue(key) : "0"));
                        continue;
                    case CLASS_DOUBLE:
                    case CLASS_DOUBLE_OBJECT:
                        field.setDouble(result, Double.parseDouble(map.getValue(key) != null && isNotEmpty(map.getValue(key)) ? map.getValue(key) : "0"));
                        continue;
                    case CLASS_BOOLEAN:
                    case CLASS_BOOLEAN_OBJECT:
                        field.setBoolean(result, Boolean.parseBoolean(isNotEmpty(map.getValue(key)) ? map.getValue(key) : "false"));
                        continue;
                    case CLASS_CHAR:
                    case CLASS_CHAR_OBJECT:
                        char nullchar = 0;
                        field.setChar(result, Character.valueOf(map.getValue(key) != null && isNotEmpty(map.getValue(key)) ? map.getValue(key).charAt(0) : nullchar));
                        continue;
                    case CLASS_BYTE:
                    case CLASS_BYTE_OBJECT:
                        field.setByte(result, Byte.parseByte(map.getValue(key) != null && isNotEmpty(map.getValue(key)) ? map.getValue(key) : "0"));
                        continue;
                }
                //ArrayList字段
                if (fieldTypeClass.equals(ArrayList.class)) {
                    //获取这个ArrayList运行时的泛型类
                    Type type = field.getGenericType();
                    Class<?> genericTypeClass = null;
                    if (type != null && type instanceof ParameterizedType) {
                        genericTypeClass = Class.forName(((ParameterizedType) type).getActualTypeArguments()[0].toString().replace("class ", ""));
                    }
                    //创建并解析这个list
                    //这里为什么要传map而不传map.get(key)呢？
                    //
                    ArrayList<?> child = new BeanListHandler(genericTypeClass).handle(map.get(key));
                    Method m = beanClass.getMethod("set" + String.valueOf(field.getName().charAt(0)).toUpperCase() + field.getName().substring(1), fieldTypeClass);
                    m.invoke(result, child);
                } else if (fieldTypeClass.equals(HashMap.class)) {
                    Type type = field.getGenericType();
                    Class<?> genericTypeClass = null;
                    if (type != null && type instanceof ParameterizedType) {
                        genericTypeClass = Class.forName(((ParameterizedType) type).getActualTypeArguments()[1].toString().replace("class ", ""));
                    }
                    HashMap<?, ?> child = new BeanMapHandler(genericTypeClass).handle(map.get(key));
                    Method m = beanClass.getMethod("set" + String.valueOf(field.getName().charAt(0)).toUpperCase() + field.getName().substring(1), fieldTypeClass);
                    m.invoke(result, child);
                }
                //其他实体类型
                else {
                    Object child = new BeanHandler(fieldTypeClass).handle(map.get(key));
                    Method m = beanClass.getMethod("set" + String.valueOf(field.getName().charAt(0)).toUpperCase() + field.getName().substring(1), fieldTypeClass);
                    m.invoke(result, child);
                }
            } catch (Exception e) {
//                e.printStackTrace();
            }
        }

        return result;
    }
}
