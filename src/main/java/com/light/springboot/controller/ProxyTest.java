package com.light.springboot.controller;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyTest {


    class ProxyHandler implements InvocationHandler {
        private Object object;

        public ProxyHandler(Object object) {
            this.object = object;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("Before invoke " + method.getName());
            method.invoke(object, args);
            System.out.println("After invoke " + method.getName());
            return null;
        }


    }


    interface HelloInterface {
        void sayHello();

        void saygoodbay();
    }


    class Hello implements HelloInterface {
        @Override
        public void sayHello() {
            System.out.println("Hello zhanghao!");
        }

        @Override
        public void saygoodbay() {
            System.out.println("caonim zhanghao!");
        }
    }


    public static void main(String[] args) {
        System.getProperties().setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        ProxyTest proxyTest = new ProxyTest();
        HelloInterface hello = proxyTest.new Hello();

        InvocationHandler handler = proxyTest.new ProxyHandler(hello);

        HelloInterface proxyHello = (HelloInterface) Proxy.newProxyInstance(hello.getClass().getClassLoader(), hello.getClass().getInterfaces(), handler);

        System.out.println();

        proxyHello.sayHello();

        proxyHello.saygoodbay();
    }
}
