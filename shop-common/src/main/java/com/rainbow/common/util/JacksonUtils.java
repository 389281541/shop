package com.rainbow.common.util;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.google.common.collect.Maps;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * 类的描述
 *
 * @author lujinwei
 * @Date 2018/11/9 14:40
 */
public abstract class JacksonUtils {
    // can reuse, share globally
    private static final ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static final String obj2Str(Object o) {
        try {
            return mapper.writeValueAsString(o);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static final void writeObj(OutputStream out, Object value) throws JsonGenerationException,
            JsonMappingException, IOException {
        mapper.writeValue(out, value);
    }

    public static final <T> T str2Obj(String s, Class<T> valueType) throws JsonParseException, JsonMappingException,
            IOException {
        JavaType javaType = getJavaType(valueType, null);
        return mapper.readValue(s, javaType);
    }

    protected static JavaType getJavaType(Type type, Class<?> contextClass) {
        return (contextClass != null) ? mapper.getTypeFactory().constructType(type, contextClass) : mapper
                .constructType(type);
    }

    public static final <T> T str2Obj(String s, TypeReference<T> valueType) throws JsonParseException,
            JsonMappingException, IOException {
        return mapper.readValue(s, valueType);
    }

    public static final <T> List<T> str2List(String s, Class<T> valueType) throws JsonParseException,
            JsonMappingException, IOException {
        JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, valueType);
        return mapper.readValue(s, javaType);
    }

    public static final <T> T readObj(InputStream in, Class<T> valueType) throws JsonParseException,
            JsonMappingException, IOException {
        return mapper.readValue(in, valueType);
    }

    @SuppressWarnings("unchecked")
    public static final <T> T readObj(InputStream in, JavaType valueType) throws JsonParseException,
            JsonMappingException, IOException {
        return (T) mapper.readValue(in, valueType);
    }

    public static final <T> T map2Obj(Map<String, Object> map, Class<T> valueType) throws JsonParseException,
        JsonMappingException, IOException {
        return (T) mapper.convertValue(map, valueType);
    }

    /**
     * NoParamConstructor required!
     *
     * @param o
     * @return
     */
    public static final Map<String, Object> obj2Map(Object o) {
        Map<String, Object> map = Maps.newHashMap();

        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(o.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                if (key.compareToIgnoreCase("class") == 0) {
                    continue;
                }
                Method getter = property.getReadMethod();
                Object value = getter != null ? getter.invoke(o) : null;
                map.put(key, value);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return map;
    }
}