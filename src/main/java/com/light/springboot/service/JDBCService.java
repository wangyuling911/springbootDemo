package com.light.springboot.service;

import com.light.springboot.mapper.DepartmentMapper;
import com.light.springboot.vo.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class JDBCService {

    @Autowired
    DepartmentMapper departmentMapper;

    @Transactional(rollbackFor = Exception.class)
    public void setDepartmentMapper() {
        Department department = new Department();
        department.setId(1428571274939330562L);
        department.setName("wang");
        departmentMapper.insert(department);

        Department department2 = new Department();
        department2.setId(1428571273269997569L);
        department2.setName("yu");
        departmentMapper.insert(department2);
    }
}
