package org.javaboy.client1;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @Author szh
 * @Date 2022/7/22 14:42
 * @PackageName:org.javaboy.client1
 * @ClassName: HelloController
 * @Description: TODO
 * @Version 1.0
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName()+ ":" + authentication.getAuthorities();
    }
}
