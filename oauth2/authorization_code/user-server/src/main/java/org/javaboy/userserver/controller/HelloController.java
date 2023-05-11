package org.javaboy.userserver.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author szh
 * @Date 2022/7/21 13:34
 * @PackageName:org.javaboy.userserver.controller
 * @ClassName: HelloController
 * @Description: TODO
 * @Version 1.0
 */
@RestController
@CrossOrigin(value = "*")
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/admin/hello")
    public String admin(){
        return "hello admin!";
    }

}
