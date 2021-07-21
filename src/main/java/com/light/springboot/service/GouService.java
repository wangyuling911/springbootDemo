package com.light.springboot.service;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * 钩子函数测试
 *
 * @author wangyuling
 */
@Service
public class GouService implements InitializingBean, DisposableBean, SmartLifecycle, CommandLineRunner {


    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("GouService  InitializingBean afterPropertiesSet()");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("GouService  DisposableBean destroy()");
    }

    @Override
    public void start() {
        System.out.println("GouService  SmartLifecycle start()");
    }

    @Override
    public void stop() {
        System.out.println("GouService  SmartLifecycle stop()");
    }

    @Override
    public boolean isRunning() {
        System.out.println("GouService  SmartLifecycle isRunning()");
        return false;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("GouService  CommandLineRunner run()");
    }

    @PostConstruct
    public void init() {
        System.out.println("GouService   @PostConstruct init()");
    }
}
