package com.baizhi.dao;

import com.baizhi.entity.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    @Select("select * from t_user where username = #{username}")
    public User selectOne(String username);
}
