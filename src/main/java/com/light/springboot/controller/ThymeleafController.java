package com.light.springboot.controller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.SameSiteCookies;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("thymeleaf")
public class ThymeleafController {

    @RequestMapping("hello")
    public String hello(HttpServletRequest request, HttpServletResponse response) {
//        ResponseCookie cookie33 = ResponseCookie.from("bage", "yellowgoril").httpOnly(true).secure(true).path("/").
//                maxAge(Duration.ofHours(1))
//                .sameSite(SameSiteCookies.NONE.getValue())
//                .build();
//        // 设置Cookie Header
//        response.setHeader(HttpHeaders.SET_COOKIE, cookie33.toString());

        Cookie cookie = new Cookie("wag2222", "yu2222");
        cookie.setMaxAge(10 * 365 * 24 * 60 * 60);
        cookie.setHttpOnly(true);
        // 设置path到根目录
        cookie.setPath("/");
        response.addCookie(cookie);
//        map.put("msg", "Hello Thymeleaf");
        return "hello";
    }

}

