package org.javaboy.jwt.config;

import org.javaboy.jwt.filter.JwtFilter;
import org.javaboy.jwt.filter.JwtLoginFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @Author szh
 * @Date 2022/5/17 11:14
 * @PackageName:org.javaboy.jwt.config
 * @ClassName: SecurityConfig
 * @Description: TODO
 * @Version 1.0
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("javaboy").password("$2a$10$tt1nm0/HL2jRtvI/DgXXbeXSmau306UdPxlTEEgY7X1jqzxoCALh.")
                .roles("user")
                .and()
                .withUser("admin").password("$2a$10$tt1nm0/HL2jRtvI/DgXXbeXSmau306UdPxlTEEgY7X1jqzxoCALh.")
                .roles("admin");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/hello").hasRole("user")
                .antMatchers("/admin").hasRole("admin")
                // 放行login请求
                .antMatchers(HttpMethod.POST, "/login")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                // 指定生成token过滤器  什么过滤器以及执行在哪个前面
                .addFilterBefore(new JwtLoginFilter("/login", authenticationManager()),
                        UsernamePasswordAuthenticationFilter.class)
                // 指定校验过滤器
                .addFilterBefore(new JwtFilter(), UsernamePasswordAuthenticationFilter.class)
                .csrf().disable();
    }
}
