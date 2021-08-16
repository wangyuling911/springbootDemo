package com.light.springboot.service;

import com.light.springboot.mapper.DepartmentMapper;
import com.light.springboot.vo.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings("ALL")
@Service
public class MybatisService {

    @Autowired
    DepartmentMapper departmentMapper;

    @Transactional
    public void update1(int i) throws Exception {
        System.out.println("1开始");
        Department department = new Department();
        department.setId(1);
        department.setName(String.valueOf(i));
        department.setDescr(String.valueOf(i));
        departmentMapper.update(department);
        System.out.println("1休眠");
        Thread.sleep(10000);
        System.out.println("1休眠结束");
        Department department2 = new Department();
        department2.setId(2);
        department2.setName(String.valueOf(i));
        department2.setDescr(String.valueOf(i));
        departmentMapper.update(department2);
        System.out.println("1执行完毕");
    }

    @Transactional
    public void update2(int i) throws InterruptedException {
        System.out.println("2开始");
        Department department = new Department();
        department.setId(2);
        department.setName(String.valueOf(i));
        department.setDescr(String.valueOf(i));
        departmentMapper.update(department);
        System.out.println("2休眠");
        Thread.sleep(10000);
        System.out.println("2休眠结束");
        Department department2 = new Department();
        department2.setId(1);
        department2.setName(String.valueOf(i));
        department2.setDescr(String.valueOf(i));
        departmentMapper.update(department2);
        System.out.println("2执行完毕");
    }

}
