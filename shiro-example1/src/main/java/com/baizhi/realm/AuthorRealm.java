package com.baizhi.realm;

import com.baizhi.dao.UserDao;
import com.baizhi.entity.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class AuthorRealm extends AuthorizingRealm {
    @Autowired
    private UserDao userDao;
    /**
     * 获取授权信息
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//        1.获取用户名

//        2.根据用户名获取角色信息和权限信息

//        3.封装info
        return null;
    }

    /**
     * 获取认证信息
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //1、从令牌中获取用户名
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken)token;
        //2、获取身份信息
        String username = usernamePasswordToken.getUsername();
        //3、查询数据库
        User user = userDao.selectOne(username);
        //4、判断根据用户名查到的用户
        if (user != null) {
            System.out.println();
            return new SimpleAuthenticationInfo(user.getUsername(),user.getPassword(),this.getName());
        }
        return null;
    }
}
