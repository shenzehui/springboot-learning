package org.javaboy.securitydy.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author szh
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    private String hello() {
        return "hello";
    }

    @GetMapping("/db/hello")
    private String db() {
        return "hello db";
    }

    @GetMapping("/admin/hello")
    private String admin() {
        return "hello admin";
    }

    @GetMapping("/user/hello")
    private String user() {
        return "hello user";
    }

}
