package com.wyl.provide.mapper;

import com.wyl.provide.vo.Department;

public interface DepartmentMapper {

    public int insert(Department department);

    public Department getById(Integer id);

    public void update(Department department);

    public void deleteById(Integer id);
}