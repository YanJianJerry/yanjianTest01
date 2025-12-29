package com.yan.myProxy;

import java.lang.reflect.Proxy;

public class TestDynamicProxy1 {
    public static void main(String[] args) {
        // 目标对象
        Character1 character = new Character1();
        // 创建代理对象1
        ObjAction1 proxy1 = (ObjAction1) Proxy.newProxyInstance(
                Character1.class.getClassLoader(),
                character.getClass().getInterfaces(),
                new MyInvocationHandler1(character)
        );
        proxy1.save();

        // 创建代理对象2
        ObjAction2 proxy2 = (ObjAction2) Proxy.newProxyInstance(
                Character1.class.getClassLoader(),
                character.getClass().getInterfaces(),
                new MyInvocationHandler1(character)
        );
        proxy2.update();

    }
}
