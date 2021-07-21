package com.light.springboot.controller;

import com.light.springboot.service.JDBCService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TestController {
    @Autowired
    JDBCService jdbcService;


    @GetMapping("/helloworld")
    public String helloworld() {
        jdbcService.setDepartmentMapper();
        return "beijign";
    }





}