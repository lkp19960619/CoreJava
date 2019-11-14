package com.baizhi.realm;

import com.baizhi.dao.UserDao;
import com.baizhi.entity.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 自定义Realm
 */
public class AuthenRealm extends AuthenticatingRealm {
    @Autowired
    private UserDao userDao;
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //1、从令牌中获取用户名
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken)token;
        //2、获取身份信息->账号
//        usernamePasswordToken.getPrincipal();
        String username = usernamePasswordToken.getUsername();
        //3、通过Dao查询数据库  获取数据库中保存的数据
        User user = userDao.selectOne(username);
        if (user != null) {
            /**
             * arg1:数据库查到的账号
             * arg2:数据库查到的密码
             * arg3:当前对象的名字
             */
            return new SimpleAuthenticationInfo(user.getUsername(),user.getPassword(),this.getName());
        }
        return null;
    }
}
