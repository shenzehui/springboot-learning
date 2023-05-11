package org.javaboy.oauthserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @Author szh
 * @Date 2022/7/22 14:34
 * @PackageName:org.javaboy.oauthserver.controller
 * @ClassName: UserController
 * @Description: TODO
 * @Version 1.0
 */
@RestController
public class UserController {

    @GetMapping("/user")
    public Principal getCurrentUser(Principal principal){
        return principal;
    }

}
