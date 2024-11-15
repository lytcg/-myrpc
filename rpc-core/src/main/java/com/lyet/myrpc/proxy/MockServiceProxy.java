package com.lyet.myrpc.proxy;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

//@Slf4j
public class MockServiceProxy implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Class<?> returnType = method.getReturnType();
        System.out.println("mock invoke "+method.getName());
        return getDefaultObject(returnType);
    }

    private Object getDefaultObject(Class<?> type) {
        //基本类型
        if(type.isPrimitive()) {
            if(type==boolean.class) {
                return false;
            }
            if(type==short.class) {
                return (short)0;
            }
            if(type==int.class) {
                return 0;
            }
            if(type==long.class) {
                return 0L;
            }
            return 0;
        }
        //对象
        return null;
    }
}
