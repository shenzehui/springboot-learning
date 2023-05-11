package org.javaboy.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author szh
 */
//@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 告诉系统密码不需要加密
     *
     * @return
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    /**
     * 配置账号和密码
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("root").password("123").roles("admin")
                .and()
                .withUser("吹嘘").password("123").roles("user");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 开启配置
        http.authorizeRequests()
                // 拦截路径 有admin角色才能访问
                .antMatchers("/admin/**").hasRole("admin")
                // 两个角色有其一就能访问
//                .antMatchers("/user/**").hasAnyRole("admin","user")
                .antMatchers("/user/**").access("hasAnyRole('admin','user')")
                // 除此之外路径认证之后就能访问
                .anyRequest().authenticated()
                .and()
                // 表单登录的详细信息
                .formLogin()
                // 进行表单登录的接口 post请求
                .loginProcessingUrl("/doLogin")
                // spring security 登录页面
                .loginPage("/login")
                // 修改用户名和密码的key值
                .usernameParameter("uname")
                .passwordParameter("pwd")
                // 登录成功后的处理
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp,
                                                        Authentication authentication) throws IOException, ServletException {
                        // 返回json格式
                        resp.setContentType("application/json;charset=utf-8");
                        PrintWriter out = resp.getWriter();
                        Map<String, Object> map = new HashMap<>();
                        // authentication.getPrincipal() 获取登录成功后的用户信息对象
                        map.put("status", 200);
                        map.put("msg", authentication.getPrincipal());
                        // 转成json输出
                        out.write(new ObjectMapper().writeValueAsString(map));
                        out.flush();
                        out.close();
                    }
                })
                // 登录失败的处理
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp,
                                                        AuthenticationException e) throws IOException, ServletException {
                        resp.setContentType("application/json;charset=utf-8");
                        PrintWriter out = resp.getWriter();
                        Map<String, Object> map = new HashMap<>();
                        map.put("status", 401);
                        if (e instanceof LockedException) {
                            // 账号锁定异常
                            map.put("msg", "账户被锁定,登录失败");
                        } else if (e instanceof BadCredentialsException) {
                            // 错误的凭证
                            map.put("msg", "用户名或密码输入错误，登录失败");
                        } else if (e instanceof DisabledException) {
                            // 账号被禁用
                            map.put("msg", "账户被禁用，登录失败");
                        } else if (e instanceof AccountExpiredException) {
                            map.put("msg", "账号过期，登录失败");
                        } else if (e instanceof CredentialsExpiredException) {
                            // 密码过期
                            map.put("msg", "密码过期，登录失败");
                        } else {
                            map.put("msg", "登录失败");
                        }
                        out.write(new ObjectMapper().writeValueAsString(map));
                        out.flush();
                        out.close();
                    }
                })
                .permitAll()
                .and()
                // 退出登录处理，返回json get请求
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest req, HttpServletResponse resp,
                                                Authentication authentication) throws IOException, ServletException {
                        resp.setContentType("application/json;charset=utf-8");
                        PrintWriter out = resp.getWriter();
                        Map<String, Object> map = new HashMap<>();
                        map.put("status", 200);
                        map.put("msg", "注销登录成功!");
                        out.write(new ObjectMapper().writeValueAsString(map));
                        out.flush();
                        out.close();
                    }
                })
                .and()
                // 防止csrf攻击
                .csrf().disable();
    }
}
