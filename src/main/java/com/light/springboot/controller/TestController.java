package com.light.springboot.controller;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.tomcat.util.http.SameSiteCookies;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

@RestController
@Slf4j
public class TestController {

    @GetMapping("/helloworld")
    public String helloworld(HttpServletRequest request, HttpServletResponse response) {


        return "beijign";
    }

    public static void main(String[] args) {
        System.out.println(RandomStringUtils.randomAlphanumeric(100));

    }

    @GetMapping("/wang")
    public String wang(HttpServletRequest request, HttpServletResponse response) {
        for (int i = 0; i < 1000; i++) {
            String s = RandomStringUtils.randomAlphanumeric(1000);
        }
        return "beijign";
    }

}