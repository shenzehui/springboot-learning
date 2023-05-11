package com.szh.oauthgithub.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by szh on 2023-05-11
 *
 * @author szh
 */

@RestController
public class HelloController {

    @GetMapping("/hello")
    public DefaultOAuth2User hello() {
        System.out.println("hello");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ((DefaultOAuth2User) authentication.getPrincipal());
    }
}
