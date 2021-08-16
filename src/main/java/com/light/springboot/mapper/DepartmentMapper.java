package com.light.springboot.mapper;

import com.light.springboot.vo.Department;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

@Component
public interface DepartmentMapper {

     int insert(Department department);

     Department getById(Integer id);

     void update(Department department);
     void deleteById(Integer id);
}