package com.wxt.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Auther: ThomasWu
 * @Date: 2021/5/30 16:23
 * @Description:
 */
public class MyInvocationHandler implements InvocationHandler {

    // 被代理的对象，实际的方法执行者
    private Object target;

    public MyInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("开启事务");

        // 执行目标对象方法
        Object result = method.invoke(target, args);

        System.out.println("提交事务");

        return result;
    }

    public Object getProxy() {
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), target.getClass().getInterfaces(), this);
    }

}
