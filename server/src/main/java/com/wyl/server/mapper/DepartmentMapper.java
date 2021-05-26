package com.wyl.server.mapper;


import com.wyl.server.vo.Department;

public interface DepartmentMapper {

    public int insert(Department department);

    public Department getById(Integer id);

    public void update(Department department);

    public void deleteById(Integer id);
}