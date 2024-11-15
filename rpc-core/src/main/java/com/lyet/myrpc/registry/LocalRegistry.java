package com.lyet.myrpc.registry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class LocalRegistry {
    /**
     * 注册信息存储
     */
    private static final Map<String,Class<?>> map=new ConcurrentHashMap<>();

    /**
     * 注册服务
     * @param name
     * @param clazz
     */
    public static void register(String name,Class<?> clazz){
        map.put(name,clazz);
    }

    /**
     * 获取服务
     * @param name
     * @return
     */
    public static Class<?> get(String name){
        return map.get(name);
    }

    /**
     * 删除服务
     * @param name
     */
    public static void remove(String name){
        map.remove(name);
    }


}
