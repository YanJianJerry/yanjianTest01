package com.yan.myProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler1 implements InvocationHandler {
    private Object target;

    public MyInvocationHandler1(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("开始事务");
        Object result = method.invoke(target, args);
        System.out.println("提交事务");
        return result;
    }
}
