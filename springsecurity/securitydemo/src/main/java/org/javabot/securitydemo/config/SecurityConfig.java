package org.javabot.securitydemo.config;

import org.javabot.securitydemo.service.HrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.session.HttpSessionEventPublisher;

/**
 * @Author szh
 * @Date 2022/5/20 15:02
 * @PackageName:org.javabot.securitydemo.config
 * @ClassName: SecurityConfig
 * @Description: TODO
 * @Version 1.0
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    HrService hrService;

    @Autowired
    MyWebAuthenticationDetailsSource myWebAuthenticationDetailsSource;

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(hrService);
    }

    /*注入自定义AuthenticationProvider 设置密码加密方法 和 userDetailService*/
    @Bean
    MyAuthenticationProvider myAuthenticationProvider(){
        MyAuthenticationProvider provider = new MyAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(hrService);
        return provider;
    }

    /*将provider加入到manager中*/
    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        /*注 AuthenticationManager 中可以存放多个 providerManager*/
        return new ProviderManager(myAuthenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin/**").hasRole("admin")
                .antMatchers("/vf/**").permitAll()
                .anyRequest().fullyAuthenticated()
                .and()
                .formLogin()
                .authenticationDetailsSource(myWebAuthenticationDetailsSource)
                .successHandler(((request, response, authentication) -> {
                    response.setContentType("text/html;charset=utf-8");
                    response.getWriter().write("success");
                }))
                .failureHandler(((request, response, exception) -> {
                    response.setContentType("text/html;charset=utf-8");
                    response.getWriter().write(exception.getMessage());
                }))
                .permitAll()
                .and()
                .csrf().disable()
                /*session管理*/
                .sessionManagement()
                /*最大的session并发数*/
                .maximumSessions(1)
                /*是否禁止后面用户登录*/
                .maxSessionsPreventsLogin(true);
    }
    /*Spring Security能够感知到客户端session的销毁，并及时清理session*/
    @Bean
    HttpSessionEventPublisher httpSessionEventPublisher(){
        return new HttpSessionEventPublisher();
    }
}
