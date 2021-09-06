package com.light.springboot.controller;

import com.light.springboot.service.JDBCService;
import com.light.springboot.service.TestEvent;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class TestController {

    @Autowired
    JDBCService jdbcService;
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    //
    @GetMapping("/helloworld")
    public String helloworld() {
        System.out.println("开始");
        applicationEventPublisher.publishEvent(new TestEvent());
        System.out.println("结束");
        return "beijign";
    }

    @GetMapping("/helloworld2")
    public String helloworld2() {
        System.out.println("开始");
        jdbcService.setDepartmentMapper();
        System.out.println("结束");
        return "beijign";
    }

    public static void main(String[] args) throws ClassNotFoundException {
        int COUNT_BITS = Integer.SIZE - 3;
        System.out.println(COUNT_BITS);

        int RUNNING = -1 << COUNT_BITS;
        System.out.println( Integer.toBinaryString(RUNNING));
        System.out.println(Integer.toBinaryString(RUNNING | 0));
    }

    @GetMapping("/helloworld3")
    public String helloworld3() throws ClassNotFoundException, InterruptedException {
        testxiancheng();
        return "成功";
    }

    @GetMapping("/helloworld4")
    public String helloworld4() throws ClassNotFoundException, InterruptedException {

        return String.valueOf(threadPoolExecutor.getPoolSize());
    }

    @GetMapping("/helloworld5")
    public String helloworld5() throws ClassNotFoundException, InterruptedException {
        threadPoolExecutor.shutdown();
        Thread.interrupted();
        Thread.currentThread().interrupt();
        Thread.currentThread().stop();
        return "true";
    }

    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(2), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());


    private void testxiancheng() {
        for (int i = 0; i < 10; i++) {
            threadPoolExecutor.execute(() -> {
                System.out.println(Thread.currentThread().getName() + "执行中");
                try {
                    Thread.sleep(1000 * 30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

    }



    @EventListener
    public void optionBaseEvent(TestEvent event) {
        System.out.println("EventListener");
        var var = "wang";

    }


    class MyTask extends TimerTask {
        int count = 0;

        @Override
        public void run() {
            try {
                //do Something
                System.out.println(new Date().toString() + ": " + count);
                count++;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    private void xiaodingdui() {
        int[] nums = new int[]{1, 5, 4, 2, 3, 6};
        System.out.println(topKMax(nums, 5));    //输出：[2, 3, 4, 5, 6]
    }


    //寻找前k个最大的数--使用小顶堆
    public static List<Integer> topKMax(int[] nums, int k) {
        //寻找前k个最小数，因此将小顶堆大小定义为k
        PriorityQueue<Integer> pq = new PriorityQueue<>(k);
        for (int i = 0; i < nums.length; i++) {
            if (i < k) {
                pq.offer(nums[i]);    //前k个数，直接入堆
            } else if (nums[i] > pq.peek()) {    //如果当前元素比堆顶元素大
                pq.poll();    //说明堆顶元素（堆中最小元素）一定不属于前k大的数，出堆
                pq.offer(nums[i]);    //当前元素有可能属于前k大，入堆
            }
        }

        List<Integer> ans = new ArrayList<>();
        while (!pq.isEmpty()) {
            ans.add(pq.poll());
        }
        return ans;
    }


}