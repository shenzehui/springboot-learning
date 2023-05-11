package org.javaboy.actuator.config;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.xml.ws.Endpoint;

/**
 * @Author szh
 * @Date 2022/5/19 13:12
 * @PackageName:org.javaboy.actuator.config
 * @ClassName: SecurityConfig
 * @Description: TODO
 * @Version 1.0
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 配置端点和Security整合
        http.requestMatcher(EndpointRequest.toAnyEndpoint()) //拦截所有的端点
                .authorizeRequests()
                .anyRequest().hasRole("ADMIN")
                .and()
                // httpBasic登录(使用postman)
                .httpBasic()
                .and()
                .csrf().disable();
    }
}
