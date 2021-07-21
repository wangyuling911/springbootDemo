package com.light.springboot.service;

import com.light.springboot.mapper.DepartmentMapper;
import com.light.springboot.vo.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JDBCService {
    @Autowired
    DepartmentMapper departmentMapper;

    public void setDepartmentMapper() {
        Department department = new Department();
        department.setName("wang");
        this.departmentMapper.insert(department);

    }
}
