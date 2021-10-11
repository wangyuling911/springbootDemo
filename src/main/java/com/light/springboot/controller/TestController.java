package com.light.springboot.controller;

import com.light.springboot.kafka.KafkaProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Queue;
import java.util.concurrent.*;

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


    public static void main(String[] args) throws InterruptedException {
        DelayQueue delayQueue = new DelayQueue();
        delayQueue.offer(new MyDelayedTask("task1", 10000));
        delayQueue.offer(new MyDelayedTask("task2", 3900));
        delayQueue.offer(new MyDelayedTask("task3", 1900));
        delayQueue.offer(new MyDelayedTask("task4", 5900));
        delayQueue.offer(new MyDelayedTask("task5", 6900));
        delayQueue.offer(new MyDelayedTask("task6", 7900));
        delayQueue.offer(new MyDelayedTask("task7", 4900));
        while (true) {
            Delayed take = delayQueue.take();

        }
    }





}