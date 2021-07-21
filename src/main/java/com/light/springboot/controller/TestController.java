package com.light.springboot.controller;

import com.light.springboot.service.JDBCService;
import com.light.springboot.service.TestEvent;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TestController {
    @Autowired
    JDBCService jdbcService;
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @GetMapping("/helloworld")
    public String helloworld() {
        System.out.println("开始");
        applicationEventPublisher.publishEvent(new TestEvent());
        System.out.println("结束");
        return "beijign";
    }

    @EventListener
    public void optionBaseEvent(TestEvent event) {
        System.out.println("EventListener");
        var var = "wang";
    }





}