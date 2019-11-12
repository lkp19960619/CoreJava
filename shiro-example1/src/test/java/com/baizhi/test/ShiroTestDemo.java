package com.baizhi.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;


public class ShiroTestDemo {
    /**
     * 模拟用户输入的用户名和密码
     */
    private String username = "zhangsan";
    private String password = "123456";

    @Test
    public void test1() {
        //1、获取配置文件中的数据，得到安全管理器工厂
        IniSecurityManagerFactory securityManagerFactory = new IniSecurityManagerFactory("classpath:static/shiro.ini");
        //2、获取安全管理器，安全管理器封装了配置文件数据【相当于得到数据库中所有的数据】
        SecurityManager securityManager = securityManagerFactory.getInstance();
        //把安全管理器给工具类 相当于Subject和安全管理器建立了联系
        SecurityUtils.setSecurityManager(securityManager);
        //3、获取Subject【通过工具类获取】->保证了在项目的任何位置获取到的对象都是同一个
        Subject subject = SecurityUtils.getSubject();
        //4、把用户输入的账号和密码给Subject对象   调用登录方法传入用户输入的账号密码[封装到token中]
        //封装到令牌
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        /**
         * login通过抛异常的方式 告诉用户有没有认证成功
         * UnknownAccountException 不知道账号异常 账号错误
         * IncorrectCredentialsException 不正确的密码 密码错误
         */
        try {
            subject.login(token);
            System.out.println("登录成功");
        } catch (Exception e) {
            System.out.println("账号或密码错误");
        }
        /**
         * isAuthenticated 查询当前主体的认证状态
         * Authenticate 认证
         */
        boolean authenticated = subject.isAuthenticated();
        System.out.println(authenticated);
    }
}
