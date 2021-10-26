package com.light.springboot.controller;


import com.light.springboot.vo.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.SameSiteCookies;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ObjectStreamClass;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.*;

@RestController
@Slf4j
public class TestController {

    @GetMapping("/helloworld")
    public String helloworld(HttpServletRequest request, HttpServletResponse response) {
        ResponseCookie cookie33 = ResponseCookie.from("bage", "yellowgoril").httpOnly(true).secure(true).path("/").
                maxAge(Duration.ofHours(1))
                .sameSite(SameSiteCookies.NONE.getValue())
                .build();
        // 设置Cookie Header
        //response.setHeader(HttpHeaders.SET_COOKIE, cookie33.toString());
        ResponseCookie cookie44 = ResponseCookie.from("bage444", "yellowgoril4444").httpOnly(true).secure(true).path("/").
                maxAge(Duration.ofHours(1))
                .sameSite(SameSiteCookies.NONE.getValue())
                .build();

        response.setHeader(HttpHeaders.SET_COOKIE, cookie44.toString());
        response.addHeader(HttpHeaders.SET_COOKIE, cookie33.toString());

//        Cookie cookie = new Cookie("wag2222", "yu2222");
//        cookie.setSecure(true);
//        cookie.setMaxAge(10 * 365 * 24 * 60 * 60);
//        cookie.setHttpOnly(true);
//        // 设置path到根目录
//        cookie.setPath("/");
//        response.addCookie(cookie);
//        response.setHeader("set-cookie");


        return "beijign";
    }

    public static void main(String[] args)
            throws InterruptedException {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

        numbers.stream().forEach(a->{
            System.out.println("北极光");
        });
        numbers.parallelStream().forEach(a->{

            System.out.println( Thread.currentThread().getName()+"===="+a);
        });

    }

}