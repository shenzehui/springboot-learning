package org.javaboy.formlogin.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

import javax.sql.DataSource;
import java.io.PrintWriter;

/**
 * @Author szh
 * @Date 2022/5/19 15:43
 * @PackageName:org.javaboy.formlogin.config
 * @ClassName: SecurityConfig
 * @Description: TODO
 * @Version 1.0
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    DataSource dataSource;

    @Override
    @Bean
    /*UserDetailsService接口：不管数据源从哪里来，数据库或者内存，都应该实现这个接口*/
    protected UserDetailsService userDetailsService() {
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
        /*sql语句查询用户是否存在*/
        if(!manager.userExists("root")){
            /*源码相当于数据库插入操作*/
            manager.createUser(User.withUsername("root").password(passwordEncoder().encode("123")).roles("admin").build());
        }
        if(!manager.userExists("szh")){
            manager.createUser(User.withUsername("szh").password(passwordEncoder().encode("123")).roles("user").build());
        }
        return manager;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/hello").hasRole("admin")
                .anyRequest().authenticated()
                .and()
                .formLogin()
//                .loginPage("login.html")
                .loginProcessingUrl("/doLogin")
                .usernameParameter("name")
                .passwordParameter("pwd")
//               /*登录成功返回json*/
                .successHandler((request, response, authentication) -> {
                    response.setContentType("application/json;charset=utf-8");
                    /*输出登录成功的用户*/
                    PrintWriter out = response.getWriter();
                    out.write(new ObjectMapper().writeValueAsString(authentication.getPrincipal()));
                    out.flush();
                    out.close();
                })
                .failureHandler((request, response, exception) -> {
                    response.setContentType("application/json;charset=utf-8");
                    PrintWriter out = response.getWriter();
                    out.write(new ObjectMapper().writeValueAsString(exception.getMessage()));
                    out.flush();
                    out.close();
                })
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(((request, response, authentication) -> {
                    response.setContentType("application/json;charset=utf-8");
                    PrintWriter out = response.getWriter();
                    out.write(new ObjectMapper().writeValueAsString("注销成功！"));
                    out.flush();
                    out.close();
                }))
                .permitAll()
                .and()
                .csrf().disable()
                /*未登录时访问接口提示*/
                .exceptionHandling()
                .authenticationEntryPoint(((request, response, authException) -> {
                    response.setContentType("application/json;charset=utf-8");
                    PrintWriter out = response.getWriter();
                    out.write(new ObjectMapper().writeValueAsString("尚未登录，请登录"));
                    out.flush();
                    out.close();
                }));
    }
}
