package com.wyl.server.service;


import org.springframework.beans.factory.FactoryBean;

public class CommandHelloWorld  implements FactoryBean {

    private final String name=null;

    public CommandHelloWorld(){
        System.out.println("构造方法执行");
    }

    public String fa() {
        System.out.println("fa方法执行");
        return  "biejign";
    }

    @Override
    public Object getObject() throws Exception {
        System.out.println("getobject");
        return "getobject";
    }

    @Override
    public Class<?> getObjectType() {
        //System.out.println("getObjectType");
        return String.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}