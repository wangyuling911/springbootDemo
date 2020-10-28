package com.light.springboot;

import com.light.springboot.mapper.DepartmentMapper;
import com.light.springboot.service.CommandHelloWorld;
import com.light.springboot.vo.Department;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.xml.soap.Node;
import java.util.concurrent.*;


public class DepartmentTest {


    @Test
    public void testInsert() {
        ExecutorService executorService = new ThreadPoolExecutor(2, 8, 60L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(1000),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("北京");
            }
        });
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("上海");
            }
        });
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("杭州");
            }
        });
        System.out.println("结束");
    }


    @Test
    public void testUpdate() {

    }


}
