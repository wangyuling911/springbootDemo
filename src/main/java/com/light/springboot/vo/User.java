package com.light.springboot.vo;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class User implements UerInf {

    private Integer id;

    private String username = "wang";

    private String password;

    private Date birthday;

    private Object target;

    public void setId(Integer id) {
        this.id = id;
    }

    public User() {

    }

    public User(Object target) {
        this.target = target;
    }

    @Override
    public void sayHello() {
        System.out.println("caocaocaoao");
    }
}