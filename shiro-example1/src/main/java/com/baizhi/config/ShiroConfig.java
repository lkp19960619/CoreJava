package com.baizhi.config;

import com.baizhi.realm.AuthenRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * shiro配置类
 * 拦截、强制登录、获取session
 */
@Configuration
public class ShiroConfig {
    //配置shiro过滤器工厂  过滤器只需要配置，不需要自己创建
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager){

        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        /**
         * 设置过滤器拦截规则
         * anon     匿名可访问      不需要认证就可以访问
         * authc    需要被认证的
         */
        //设置哪些资源需要认证，哪些资源不需要认证
        Map map = new HashMap();
        map.put("/login.jsp","anon");
        map.put("/main/*","authc");
        factoryBean.setFilterChainDefinitionMap(map);

        //设置安全管理器
        factoryBean.setSecurityManager(securityManager);
        return factoryBean;
    }

    /**
     * 创建安全管理器
     * @return
     * @Bean 加在方法上 方法的形参 如果对应的类型的对象在工厂里面有 会自动装配上
     */
    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(AuthenRealm authenRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //设置指定的Realm
        securityManager.setRealm(authenRealm);
        return securityManager;
    }

    /**
     * 创建自定义的AuthenRealm
     */
    @Bean
    public AuthenRealm getAuthenRealm(){
        return new AuthenRealm();
    }


}
