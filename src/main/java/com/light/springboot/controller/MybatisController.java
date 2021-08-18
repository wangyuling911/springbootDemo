package com.light.springboot.controller;

import com.light.springboot.mapper.DepartmentMapper;
import com.light.springboot.service.MybatisService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.TreeSet;

@SuppressWarnings("Duplicates")
@RestController
public class MybatisController {
    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    MybatisService mybatisService;


    @SneakyThrows
    @GetMapping("/mybatis")
    public String setDepartmentMapper(int i) {
        mybatisService.update1(i);
        return "success";
    }


    @GetMapping("/mybatisUpdate")
    public String setDepartmentMapper2(int i) throws InterruptedException {
        mybatisService.update2(i);

        HashMap hashMap = new HashMap();
        return "success";
    }


}
