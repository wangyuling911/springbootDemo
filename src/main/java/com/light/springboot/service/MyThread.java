package com.light.springboot.service;

import lombok.SneakyThrows;

public class MyThread {

    public int s;
    Thread thread;

    @SneakyThrows
    public void run() {
        thread = Thread.currentThread();
        while (true) {
            System.out.println("sleep start--" + System.currentTimeMillis());
            Thread.sleep(s);
            System.out.println("sleep end》》》" + System.currentTimeMillis());
        }
    }

    @Test
    public void stopMy() {
        thread.interrupt();
    }

}
