package com.light.springboot.mapper;

import com.light.springboot.vo.Department;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DepartmentMapper {

    public int insert(Department department);

    public Department getById(Integer id);

    public void update(Department department);

    public void deleteById(Integer id);
}