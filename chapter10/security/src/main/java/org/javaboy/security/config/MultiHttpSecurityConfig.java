package org.javaboy.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 多个HttpSecurity配置
 * @author szh
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true) // 开启方法安全
public class MultiHttpSecurityConfig {

    /**
     * 声明加密方式
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("root").password("$2a$10$SCdbepejhYJfleLvquZPfuPkkBkjUkHHGku0ndTG6lO6FOm7jnjUW").roles("admin")
                .and()
                .withUser("吹嘘").password("$2a$10$y6AhfGx6gO.VfF8gJMwnPuz8Z.xL7UP5ue0B5FrXrCcsCpQ.4JNYu").roles("user");
    }

    @Configuration
    @Order(1) // 设置高优先级
    public static class AdminSecurityConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            // 这种路径统统都由 admin 角色才能访问
            http.antMatcher("/admin/**").authorizeRequests().anyRequest().hasRole("admin");
        }
    }

    @Configuration
    public static class OtherSecurity extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests().anyRequest().authenticated()
                    .and()
                    .formLogin()
                    .loginProcessingUrl("/doLogin")
                    .permitAll()
                    .and()
                    .csrf().disable();
        }
    }

}
