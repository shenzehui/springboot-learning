package com.szh.oauthgithub.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 用来对 SpringSecurity 自定义配置
 * Created by szh on 2023-05-11
 *
 * @author szh
 */

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().authenticated().and()
                // 使用 oauth2 认证 配置文件中配置认证服务器
                .oauth2Login();
    }
}
