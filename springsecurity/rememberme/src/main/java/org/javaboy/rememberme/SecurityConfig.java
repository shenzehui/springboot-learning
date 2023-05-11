package org.javaboy.rememberme;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;

import javax.sql.DataSource;

/**
 * @Author szh
 * @Date 2022/5/20 12:37
 * @PackageName:org.javaboy.rememberme
 * @ClassName: SecurityConfig
 * @Description: TODO
 * @Version 1.0
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Bean
    JdbcTokenRepositoryImpl jdbcTokenRepository(){
        JdbcTokenRepositoryImpl repository = new JdbcTokenRepositoryImpl();
        repository.setDataSource(dataSource);
        return repository;
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin").password(passwordEncoder().encode("123")).roles("admin");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
//                /*二次校验：敏感数据  必须使用用户名密码登录才能访问  使用rememberme方式无法访问*/
                .antMatchers("/admin/**").fullyAuthenticated()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .permitAll()
                .and()
                /*rememberMe 客户端重启无需登录*/
                .rememberMe()
                /*指定key 服务端重启都可以直接登录*/
                /*若不指定，每次生成的是UUID值，每次都不一样，所以服务端每次重启后就无法与客户端的md5值一致*/
                .key("javaboy")
                .tokenRepository(jdbcTokenRepository())
                /*配置过期时间*/
//                .tokenValiditySeconds()
                .and()
                .csrf().disable();
    }
}
