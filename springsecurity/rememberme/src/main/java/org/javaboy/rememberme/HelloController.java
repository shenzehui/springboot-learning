package org.javaboy.rememberme;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author szh
 * @Date 2022/5/20 12:37
 * @PackageName:org.javaboy.rememberme
 * @ClassName: HelloController
 * @Description: TODO
 * @Version 1.0
 */
@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello(){
        return "hello security";
    }

    @GetMapping("/admin")
    public String admin(){
        return "hello admin";
    }
}
