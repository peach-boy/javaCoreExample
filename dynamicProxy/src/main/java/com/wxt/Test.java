package com.wxt;

import com.wxt.cglib.CglibProxy;
import com.wxt.jdk.MyInvocationHandler;

/**
 * Hello world!
 */
public class Test {
    public static void main(String[] args) {
        //JDK动态代理
    /*    PayService service = new PayServiceImpl();
        MyInvocationHandler handler = new MyInvocationHandler(service);
        PayService proxy = (PayService) handler.getProxy();
        proxy.pay();*/

        //cglib动态代理
        CglibProxy cglibProxy = new CglibProxy();
        //通过生成子类的方式创建代理类
        PayServiceImpl payServiceCglib = (PayServiceImpl) cglibProxy.getProxy(PayServiceImpl.class);
        payServiceCglib.pay();
    }
}
