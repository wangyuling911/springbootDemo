package com.light.springboot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TestController {


    @GetMapping("/helloworld")
    public String helloworld() {
        log.info("beijign ");
        return "beijign";
    }


}