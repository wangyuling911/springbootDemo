package com.light.springboot.controller;

import com.light.springboot.util.ResponseCookie;
import com.light.springboot.vo.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.SameSiteCookies;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ObjectStreamClass;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.time.Duration;
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
        response.setHeader(HttpHeaders.SET_COOKIE, cookie33.toString());

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
//        List<GarbageCollectorMXBean> garbageCollectorMXBeans = ManagementFactory.getGarbageCollectorMXBeans();
//        garbageCollectorMXBeans.forEach(a->{
//            System.out.println(a.getName());
//        });
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 10,
                100L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
        threadPoolExecutor.allowCoreThreadTimeOut(true);

    }

}