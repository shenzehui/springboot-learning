package org.javaboy.securityjson.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author szh
 * @Date 2022/5/17 10:58
 * @PackageName:org.javaboy.securityjson.controller
 * @ClassName: HelloController
 * @Description: TODO
 * @Version 1.0
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello security";
    }
}
