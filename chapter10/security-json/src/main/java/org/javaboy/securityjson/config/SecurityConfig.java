package org.javaboy.securityjson.config;

import org.javaboy.securityjson.filter.MyAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @Author szh
 * @Date 2022/5/17 10:52
 * @PackageName:org.javaboy.securityjson.config
 * @ClassName: SecurityConfig
 * @Description: TODO
 * @Version 1.0
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().authenticated()
                .and()
                .formLogin().permitAll()
                .and()
                .csrf().disable();
        // 添加过滤器
        http.addFilterAt(myAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    MyAuthenticationFilter myAuthenticationFilter() throws Exception {
        MyAuthenticationFilter filter = new MyAuthenticationFilter();
        // 这里也可以配置登录成功或失败的返回地址
        filter.setAuthenticationManager(authenticationManagerBean());
        return filter;
    }
}
