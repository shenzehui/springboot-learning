package org.javaboy.formlogin.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

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

    /*方法一*/
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("admin").password(passwordEncoder().encode("123")).roles("admin")
//                .and()
//                .withUser("szh").password(passwordEncoder().encode("123")).roles("user");
//    }

    /*方法二配置用戶：注入UserDetailService*/
    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("root").password(passwordEncoder().encode("123")).roles("admin").build());
        manager.createUser(User.withUsername("szh").password(passwordEncoder().encode("123")).roles("user").build());
        return manager;
    }

    /*角色继承*/
    @Bean
    RoleHierarchy roleHierarchy(){
        RoleHierarchyImpl hierarchy = new RoleHierarchyImpl();
        hierarchy.setHierarchy("ROLE_admin > ROLE_user");
        return hierarchy;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/js/**","/css/**","/images/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                /*从上往下匹配*/
                /*所有请求只要认证之后就能访问，后面的拦截规则就无效了*/
//                .antMatchers("/**").authenticated()
                .antMatchers("/admin/**").hasRole("admin")
                .antMatchers("/user/**").hasRole("user")
                /*anyRequest只能放在最后！！！*/
                .anyRequest().authenticated()
                .and()
                .formLogin()
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
