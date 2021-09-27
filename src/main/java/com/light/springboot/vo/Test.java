package com.light.springboot.vo;

public class Test {
    public  static int a;
    public  static int b = 4;
    public int c = 5;

    static {
        System.out.println("test的静态代码块");
    }

    public Test(){
        System.out.println("test的构造方法");
    }
}
