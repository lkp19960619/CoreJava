package com.atguigu.mp.mapper;

import com.atguigu.mp.beans.Employee;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * @Author: 李开鹏
 * @Date: 2020/5/22 23:41
 * @Version 1.0
 * @Description
 *
 * Mapper接口
 *
 * 基于MyBatis：在Mapper接口中编写CRUD相关的方法，提供Mapper接口所对应的SQL映射文件以及方法对应的SQL语句
 *
 * 基于MP：让XxxMapper接口集成BaseMapper即可
 *         BaseMapper<T>：泛型指的是当前Mapper接口所操作实体类的类型
 **/
public interface EmployeeMapper extends BaseMapper<Employee> {

}
