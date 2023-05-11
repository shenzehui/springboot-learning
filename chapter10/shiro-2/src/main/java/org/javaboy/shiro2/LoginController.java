package org.javaboy.shiro2;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author szh
 * @Date 2022/5/17 10:29
 * @PackageName:org.javaboy.shiro2
 * @ClassName: LoginController
 * @Description: TODO
 * @Version 1.0
 */
@RestController
public class LoginController {

    @GetMapping("/hello")
    public String hello() {
        return "hello shiro";
    }

    /**
     * 这里可以改为页面
     *
     * @return
     */
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
            System.out.println("fail login >>" + e.getMessage());
        }

    }
}
