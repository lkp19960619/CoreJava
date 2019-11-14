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

    @Test
    public void test2() {
        /**
         * 先认证
         */
//        1.读取配置文件，得到安全管理器工厂
        IniSecurityManagerFactory securityManagerFactory = new IniSecurityManagerFactory("classpath:static/shiro.ini");
//        2.获去安全管理器
        SecurityManager securityManager = securityManagerFactory.getInstance();
//        3.把安全管理器给工具类，相当于Subject和安全管理器建立了联系
        SecurityUtils.setSecurityManager(securityManager);
//        4.通过安全管理器获取主体
        Subject subject = SecurityUtils.getSubject();
//        5.封装令牌 完成认证
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
            System.out.println("成功");
        } catch (Exception e) {
            System.out.println("失败");
        }
        /**
         * 授权
         */
//        获取认证状态
        boolean authenticated = subject.isAuthenticated();
        if (authenticated) {
//            认证成功 开始授权[代码式授权：在java代码中校验权限]、[注解式授权：通过注解校验权限]、[标签式授权：在页面中校验权限]
            //isPermitted 查看用户有没有某个权限
            boolean permitted = subject.isPermitted("guru:delete");
            if (permitted) System.out.println("zhangsan有删除上师的权限");

            //根据角色校验
            boolean vip1 = subject.hasRole("vip1");
            if(vip1){
                System.out.println("有角色vip1可以调用删除上师的方法");
            }else{
                System.out.println("没有该角色，不能调用删除上师的方法");
            }
        }
    }


}
