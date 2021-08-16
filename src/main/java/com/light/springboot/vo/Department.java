package com.light.springboot.vo;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "department")
@Data
public class Department implements Serializable {
    @Id
    private Integer id;

    private String name;

    private String descr;


}