package com.light.springboot.service;

public class MoreThreadserive {
    public String str;

    public MoreThreadserive(String str) {
        this.str = str;
    }
    public void threadMeth(){
        for (int i = 0; i < 10; i++) {
            System.out.println(str+":"+i);
        }
    }
}
