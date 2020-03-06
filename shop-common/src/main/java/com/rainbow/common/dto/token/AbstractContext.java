package com.rainbow.common.dto.token;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * ThreadLocal基础类
 *
 * @author lujinwei
 * @since 2019-01-07
 */
public abstract class AbstractContext {

    /**
     * 存储在ThreadLocal的KEY前缀
     */
    private final static String BASE_TX_CONTEXT = "RB_CONTEXT_";

    protected static final ThreadLocal<Map<String, Object>> cache = ThreadLocal.withInitial(() -> Maps.newHashMap());

    /**
     * 初始化
     */
    protected abstract void init();

    protected static <T> void set(String key, T value) {
        cache.get().put(BASE_TX_CONTEXT + key, value);
    }

    protected static Object get(String key) {
        return cache.get().get(BASE_TX_CONTEXT + key);
    }

    protected static <T> T get(String key, Class<T> clazz) {
        return (T) cache.get().get(BASE_TX_CONTEXT + key);
    }

    public static void clear() {
        cache.remove();
    }

}
