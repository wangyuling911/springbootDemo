package com.light.springboot.service;

import com.light.springboot.mapper.DepartmentMapper;
import org.elasticsearch.common.recycler.Recycler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JDBCService {
    @Autowired
    DepartmentMapper departmentMapper;

    public void setDepartmentMapper(DepartmentMapper departmentMapper) {

    }
}
