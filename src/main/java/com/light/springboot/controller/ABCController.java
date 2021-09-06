package com.light.springboot.controller;

import com.light.springboot.service.JDBCService;
import com.light.springboot.vo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class ABCController implements ApplicationContextAware {

    @Autowired
    JDBCService jdbcService;


    @RequestMapping("/ali")
    public String helloworld() {
        return "hello.html";
    }


    public static void main(String[] args) throws NoSuchMethodException, ClassNotFoundException {
        Class<?> aClass = ClassLoader.getSystemClassLoader().loadClass("com.light.springboot.vo.User");
        long start = System.currentTimeMillis();
        System.out.println(start);
        for (int i = 0; i < 1000; i++) {
            User user = new User();
            user.sayHello();
        }
        long end = System.currentTimeMillis();
        System.out.println(end);
        System.out.println("new:" + (end - start));
    }

    public static void maindsfr(String[] args) throws NoSuchMethodException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> aClass = ClassLoader.getSystemClassLoader().loadClass("com.light.springboot.vo.User");
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            User o = (User) Class.forName("com.light.springboot.vo.User").newInstance();
            o.sayHello();
        }
        long end = System.currentTimeMillis();
        System.out.println("new" + (end - start));
    }


    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}