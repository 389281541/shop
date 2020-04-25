package com.rainbow.common.util;

import com.alibaba.fastjson.JSON;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * BeanUtils工具类
 *
 * @author lujinwei
 * @since 2020/4/23
 */
public class PortalUtils {


    public static <T> String beanToString(T value) {
        if(value == null) {
            return null;
        }
        Class<?> clazz = value.getClass();
        if(clazz == int.class || clazz == Integer.class) {
            return ""+value;
        }else if(clazz == String.class) {
            return (String)value;
        }else if(clazz == long.class || clazz == Long.class) {
            return ""+value;
        }else {
            return JSON.toJSONString(value);
        }
    }

    public static <T> T stringToBean(String str, Class<T> clazz) {
        if(str == null || str.length() <= 0 || clazz == null) {
            return null;
        }
        if(clazz == int.class || clazz == Integer.class) {
            return (T)Integer.valueOf(str);
        }else if(clazz == String.class) {
            return (T)str;
        }else if(clazz == long.class || clazz == Long.class) {
            return  (T)Long.valueOf(str);
        }else {
            return JSON.toJavaObject(JSON.parseObject(str), clazz);
        }
    }

    public static String generateParentOrderNO(Long customerId) {
        StringBuilder sb = new StringBuilder();
        String datetime = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        sb.append(datetime);
        sb.append(String.format("%06d", customerId));
        return sb.toString();
    }

}