package org.javaboy.shiro1.controller;

import org.apache.catalina.User;
import org.apache.catalina.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author szh
 * @Date 2022/5/17 10:07
 * @PackageName:org.javaboy.shiro1.controller
 * @ClassName: HelloController
 * @Description: TODO
 * @Version 1.0
 */
@RestController
public class HelloController {

    @GetMapping("/login")
    public String login() {
        return "please login";
    }

    @PostMapping("/doLogin")
    public void doLogin(String username, String password) {
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(new UsernamePasswordToken(username, password));
            System.out.println("success");
        } catch (AuthenticationException e) {
            e.printStackTrace();
            System.out.println("fail>>" + e.getMessage());
        }
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello shiro";
    }
}
