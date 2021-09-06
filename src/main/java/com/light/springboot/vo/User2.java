package com.light.springboot.vo;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class User2 implements UerInf {

    private Integer id;

    private String username = "yu";

    private String password;

    private Date birthday;


    @Override
    public void sayHello() {

    }
}