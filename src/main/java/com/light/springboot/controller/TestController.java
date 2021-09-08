package com.light.springboot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@Slf4j
public class TestController {




    @GetMapping("/helloworld")
    public String helloworld() {
        return "beijign";
    }

    public static void main(String[] args) {
        HashMap hashMap = new HashMap(12);
        hashMap.put("fdsaf","f");
    }

}