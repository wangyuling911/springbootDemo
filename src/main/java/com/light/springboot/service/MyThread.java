package com.light.springboot.service;

import com.google.common.collect.Lists;
import lombok.SneakyThrows;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyThread implements InitializingBean {

    public int s;
    Thread thread;

    @SneakyThrows
    public void run() {
        thread = Thread.currentThread();
        while (true) {
            System.out.println("sleep start--" + System.currentTimeMillis());
            Thread.sleep(s);
            System.out.println("sleep end》》》" + System.currentTimeMillis());
        }
    }


    @Override
    public void afterPropertiesSet() throws Exception {

        System.out.println("MyThread  加载完成 ");
    }
}
