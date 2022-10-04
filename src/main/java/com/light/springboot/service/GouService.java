package com.light.springboot.service;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * 钩子函数测试
 *
 * @author wangyuling
 */
@Service
public class GouService implements  //属性赋值是具体的方法不是钩子函数
        DisposableBean,
        SmartLifecycle,
        CommandLineRunner,//项目加载完时候
        BeanNameAware, //设置bean名称
        BeanClassLoaderAware,//可以设置
        BeanFactoryAware,
        BeanPostProcessor , //处理器 后置处理器 两个方法
        InitializingBean,  //叫初始化
        SmartInitializingSingleton,
        ApplicationContextAware
{

    @Value("wang")
    private String a;


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
        System.out.println("GouService  setBeanName run()");
        System.out.println(this.a);

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
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }
}
