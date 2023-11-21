package com.light.springboot.controller;

import com.google.common.collect.Lists;
import com.light.springboot.service.JDBCService;
import com.light.springboot.service.TestEvent;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.semver4j.Semver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

@RestController
@Slf4j
public class TestController {


    @Autowired
    JDBCService jdbcService;
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    //
    @GetMapping("/helloworld")
    public String helloworld(HttpServletRequest request, HttpServletResponse response) {

        System.out.println(request.getHeader("sign"));
        return "beijign";
    }

    @SneakyThrows
    @GetMapping("/helloworld2")
    public String helloworld2() {
        System.out.println("开始");
        jdbcService.setDepartmentMapper();
        System.out.println("结束");
        Thread.sleep(55);
        return "beijign";
    }

//    public static void main(String[] args) throws ClassNotFoundException {
//
//        String number = "997";
//        int digits = 5;
//        char paddingChar = '0';
//
//        String paddedNumber = String.format("%" + digits + "s", number).replace(' ', paddingChar);
//        System.out.println(paddedNumber);
//
//
////
////        String str = "0009050";
////        String prefix = "0";
////
////        Pattern pattern = Pattern.compile("^" + Pattern.quote(prefix) + "+");
////        String result = pattern.matcher(str).replaceFirst("");
////        System.out.println(result);
//
//    }
    public static void main(String[] args) {
        boolean valid = Semver.isValid("a.0.0");


        System.out.println(valid);



    }



//        /**
//     * 去"0"
//     *
//     * @param LongVersion
//     * @return
//     */
//    private String getShortVersion(String LongVersion) {
//        Pattern pattern = Pattern.compile("^" + Pattern.quote(COVERING) + "+");
//        String[] split = LongVersion.split("\\.");
//        StringBuilder stringBuilder = new StringBuilder();
//        for (int i = 0; i < split.length; i++) {
//            stringBuilder.append(pattern.matcher(LongVersion).replaceFirst(""));
//        }
//        //todo 最后一位要也是0那
//        return stringBuilder.toString();
//    }

    public static int maxSubArray(int[] nums) {
        int pre = 0, maxAns = nums[0];
        for (int x : nums) {
            pre = Math.max(pre + x, x);
            maxAns = Math.max(maxAns, pre);
        }
        return maxAns;
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