package org.javabot.securitydemo.config;

import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author szh
 * @Date 2022/5/20 16:26
 * @PackageName:org.javabot.securitydemo.config
 * @ClassName: MyWebAuthenticationDetailsSource
 * @Description: TODO
 * @Version 1.0
 */
@Component
public class MyWebAuthenticationDetailsSource implements AuthenticationDetailsSource<HttpServletRequest,MyWebAuthenticationDetails> {

    @Override
    public MyWebAuthenticationDetails buildDetails(HttpServletRequest context) {
        return new MyWebAuthenticationDetails(context);
    }
}
