package org.javaboy.shiro2;

import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.text.TextConfigurationRealm;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author szh
 * @Date 2022/5/17 10:24
 * @PackageName:org.javaboy.shiro2
 * @ClassName: ShiroConfig
 * @Description: TODO
 * @Version 1.0
 */
@Configuration
public class ShiroConfig {

    @Bean
    Realm realm() {
        TextConfigurationRealm realm = new TextConfigurationRealm();
        // 定义用户和权限
        realm.setUserDefinitions("javaboy=123,user \n admin=123,admin");
        // 设置角色权限
        realm.setRoleDefinitions("admin=read,write \n user=read");
        return realm;
    }

    /**
     * 配置拦截规则
     */
    @Bean
    ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition definition = new DefaultShiroFilterChainDefinition();
        definition.addPathDefinition("/doLogin", "anon");
        definition.addPathDefinition("/**", "authc");
        return definition;
    }
}
