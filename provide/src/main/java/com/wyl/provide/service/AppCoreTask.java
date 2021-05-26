package com.wyl.provide.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class AppCoreTask {

    @Scheduled(cron = "0 12 15 * * ? ")
    public void tesk() {
        System.out.print("开启定时任务" + new Date());
    }
}
