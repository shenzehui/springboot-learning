package org.javaboy.formlogin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

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

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("javaboy").password(passwordEncoder().encode("123")).roles("admin")
                .and()
                .withUser("szh").password(passwordEncoder().encode("123")).roles("admin");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        /*放行静态资源*/
        web.ignoring().antMatchers("/js/**","/css/**","/images/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*开启配置*/
        http.authorizeRequests()
                .antMatchers("/hello").hasRole("admin")
                /*所有请求都需要认证才能访问*/
                .anyRequest().authenticated()
                /*闭合*/
                .and()
                /*表单登录配置*/
                .formLogin()
                /*
                * 注意点：springsecurity登录请求是post请求 默认是/login.html，参数默认是username和password 当然也可以自定义修改
                * 这里配置的是get请求到login.html页面，由login.html页面再发起post请求实现登录
                * */
                /*配置登录页面*/
                .loginPage("/login.html")
                /*配置登录接口*/
                .loginProcessingUrl("/doLogin")
                /*自定义表单提交参数*/
                .usernameParameter("name")
                .passwordParameter("pwd")

                /*服务端跳转 登录成功后跳转的地址 默认是 '/' 缺点：不管你从哪里登录的，都会跳转到这个页面，不会再跳转到原页面*/
//                .successForwardUrl("/success")

                /*这是一个重定向 会记录重定向地址，登录从哪个页面来就会回到哪个页面 alwaysUse默认是false，如果配置了true，则与上面方法一致*/
                /*这种方法一般试用于单体项目*/
                .defaultSuccessUrl("/success",false)

                /*这种方法适用于前后端分离*/
//                .successHandler()

                /*跟登录相关的页面放行，不拦截*/
                .permitAll()
                .and()
                /*注销配置*/
                .logout()
                /*注销接口地址，默认'/logout' get请求*/
//                .logoutUrl("/logout")
                /*
                * 配置 注销接口和请求方式
                * */
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout","POST"))
                /*配置注销成功返回页面*/
                .logoutSuccessUrl("/login.html")
                /*是否让session失效 默认true*/
                .invalidateHttpSession(true)
                /*清除认证信息 默认true*/
                .clearAuthentication(true)
                .permitAll()
                .and()
                .csrf().disable();
    }
}
