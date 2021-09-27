package com.light.springboot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedList;

@RestController
@Slf4j
public class RedisController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @GetMapping("/redis")
    public String helloworld() {
        String wang = stringRedisTemplate.opsForValue().get("wang");


        return "zhen";
    }

    public static void main(String[] args) {
        LinkedList<Object> objects = new LinkedList<>();

    }

}