package com.light.springboot.controller;

import com.light.springboot.service.MyThread;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public String stop() {
        myThread.stopMy();
        return "beijign";
    }

}