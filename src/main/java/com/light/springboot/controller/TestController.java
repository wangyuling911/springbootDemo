package com.light.springboot.controller;

import com.light.springboot.vo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    private final static Logger logger= LoggerFactory.getLogger(TestController.class);
    @GetMapping("/helloworld")
    public String helloworld() {
        logger.error("王玉岭");
        User user = new User();
        user.setId(96);
        user.setUsername("王玉岭");
        return "王玉岭";
    }
}