package com.light.springboot.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Department implements Serializable {

    private Long id;

    private String name;

    private String descr;


}