package com.light.springboot.service;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * 钩子函数测试
 *
 * @author wangyuling
 */
//@Service
    //SmartInitializingSingleton   这个是干啥的
public class GouService implements InitializingBean, DisposableBean, SmartLifecycle,
        CommandLineRunner, BeanNameAware, BeanClassLoaderAware, BeanFactoryAware
        , BeanPostProcessor ,SmartInitializingSingleton, ApplicationListener
{



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
        return true;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("GouService  CommandLineRunner run()");
    }

    @PostConstruct
    public void init() {
        System.out.println("GouService   @PostConstruct init()");
    }

    @Override
    public void setBeanName(String name) {

    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {

    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {

    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("GouService  BeanPostProcessor postProcessBeforeInitialization()");
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("GouService  BeanPostProcessor postProcessAfterInitialization()");
        return null;
    }

    @Override
    public void afterSingletonsInstantiated() {

    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {

    }
}
