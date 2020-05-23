package com.atguigu.mp;

import com.atguigu.mp.beans.Employee;
import com.atguigu.mp.mapper.EmployeeMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

/**
 * @Author: 李开鹏
 * @Date: 2020/5/22 22:34
 * @Version 1.0
 * @Description
 **/
public class TestMP {
    private ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

    private EmployeeMapper employeeMapper = context.getBean("employeeMapper",EmployeeMapper.class);

    @Test
    public void testDataSource() throws SQLException {
        DataSource dataSource = context.getBean("dataSource", DataSource.class);
        System.out.println(dataSource);

        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }

    @Test
    public void testCommonInsert(){
        Employee employee = new Employee(5,"peng","lkp1107440885@163.com",1,24);
        Integer result = employeeMapper.insert(employee);
        System.out.println(result);
    }

    @Test
    public void testCommonUpdate(){
        Employee employee = new Employee();
        employee.setId(6);
        employee.setLastName("demo");
        employee.setEmail("1107440885@qq.com");
        Integer result = employeeMapper.updateById(employee);
        System.out.println(result);

    }

    @Test
    public void testCommonSelectById(){
        Employee employee = employeeMapper.selectById(6);
        System.out.println(employee);
    }

    @Test
    public void testCommonSelectOne(){
        //TODO：使用于多列名查询
        Employee employee = new Employee();
        employee.setId(6);
        employee.setLastName("lkp");
        Employee employee1 = employeeMapper.selectOne(employee);
        System.out.println(employee1);
    }

    @Test
    public void testCommonSelectBatchIds(){
        //TODO：批量查询
        Integer[] ids = {1,2,3};
        List<Employee> employees = employeeMapper.selectBatchIds(Arrays.asList(ids));
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    @Test
    public void testCommonSelectByMap(){
        //TODO：多列名查询
        Map<String,Object> columnMap = new HashMap<String, Object>();
        columnMap.put("last_name","demo");
        columnMap.put("gender",1);
        List<Employee> employees = employeeMapper.selectByMap(columnMap);
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    @Test
    public void testCommonSelectPage(){
        //TODO：分页查询

        List<Employee> employees = employeeMapper.selectPage(new RowBounds(2, 2), null);
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    @Test
    public void testCommonDelete(){
        //TODO：删除
        Integer result = employeeMapper.deleteById(6);
        System.out.println(result);
    }
}
