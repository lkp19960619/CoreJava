package com.atguigu.mp.beans;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

/**
 * @Author: 李开鹏
 * @Date: 2020/5/22 21:44
 * @Version 1.0
 * @Description javaBean
 **/
@Data
@TableName(value = "tbl_employee")
public class Employee {
    private Integer id;
    private String lastName;
    private String email;
    private Integer gender;
    private Integer age;

    public Employee(){}

    public Employee(Integer id, String lastName, String email, Integer gender, Integer age) {
        this.id = id;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.age = age;
    }
}
