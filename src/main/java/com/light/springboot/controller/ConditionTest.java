package com.light.springboot.controller;

import com.google.common.annotations.VisibleForTesting;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {

    public static void main11(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        new Thread(() -> {
            lock.lock();
            try {
                System.out.println("1");
                Thread.sleep(10000000);
                lock.unlock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            System.out.println("八嘎");
            lock.lock();
            try {
                System.out.println("2");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }

    public static void main2(String[] args) throws InterruptedException {
        LinkedBlockingDeque<String> objects = new LinkedBlockingDeque<>();
//        objects.add("wang");
//        objects.add("yu");
//        objects.add("ling");
        String peek = objects.peek();
        System.out.println(1);
        String poll = objects.poll();
        System.out.println(2);
//        String pop = objects.pop();
//        System.out.println(3);
        objects.take();
        System.out.println(4);
    }

    public static void main(String[] args) throws InterruptedException {
        DelayQueue<Delayed> delayeds = new DelayQueue<>();
        Delayed take = delayeds.take();
    }


}
