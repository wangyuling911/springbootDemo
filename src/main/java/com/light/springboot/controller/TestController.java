package com.light.springboot.controller;

import com.google.common.util.concurrent.RateLimiter;
import com.light.springboot.service.MyThread;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.lang.reflect.Method;
import java.util.*;

@RestController
@Slf4j
public class TestController {

    MyThread myThread;

    @GetMapping("/helloworld")
    public String helloworld(int s) {
        myThread = new MyThread();
        myThread.s = s;
        myThread.run();
        return "beijign";
    }

    @GetMapping("/stop")
    public String stop() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            System.out.println(i+"babbabge");
            Thread.sleep(1000);
        }
        return "beijign";
    }

    public static void main21(String[] args) {

        List<GarbageCollectorMXBean> garbageCollectorMXBeans = ManagementFactory.getGarbageCollectorMXBeans();
        garbageCollectorMXBeans.forEach(a -> {
            System.out.println(a.getName());
        });
    }

    public void sdfsa(){
        Method[] methods = getClass().getMethods();
        System.out.println(methods.length);
    }

    public static void main(String[] args) {
        TestController testController = new TestController();
        testController.sdfsa();

    }

}