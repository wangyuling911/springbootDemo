package com.light.springboot.controller;

import com.light.springboot.kafka.KafkaProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ArrayBlockingQueue;

@RestController
@Slf4j
public class TestController {

    @Autowired
    KafkaProducer kafkaProducer;

    @GetMapping("/helloworld")
    public String helloworld(String wang) {

        kafkaProducer.send(wang);
        return "王";
    }

    @GetMapping("/delete")
    public String delete() {

        return String.valueOf("dad");
    }

    @GetMapping("/createIndex")
    public String createIndex() {


        return String.valueOf("dafs");
    }

    @GetMapping("/putMapping")
    public String putMapping() {

        return String.valueOf("dads");
    }

    @GetMapping("/search")
    public String search() {
        return String.valueOf(true);
    }


}