package org.javaboy.shiro1.config;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.javaboy.shiro1.realm.MyRealm;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author szh
 * @Date 2022/5/17 10:01
 * @PackageName:org.javaboy.shiro1.config
 * @ClassName: ShiroConfig
 * @Description: TODO
 * @Version 1.0
 */
@Configuration
public class ShiroConfig {
    @Bean
    MyRealm myRealm() {
        return new MyRealm();
    }

    @Bean
    SecurityManager securityManager() {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(myRealm());
        return manager;
    }

    @Bean
    ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager());
        // 没有登录访问的页面
        bean.setLoginUrl("/login");
        // 成功地址
        bean.setSuccessUrl("/index");
        // 定义拦截规则
        Map<String, String> map = new LinkedHashMap<>();
        // 匿名访问登录接口，无需认证
        map.put("/doLogin", "anon");
        // 需要认证登录的接口
        map.put("/**", "authc");
        bean.setFilterChainDefinitionMap(map);
        return bean;
    }
}
