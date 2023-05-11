package org.javaboy.tomcat.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author szh
 */
@RestController
public class HelloConrtoller {

    @GetMapping("/hello")
    public String hello() {
        return "hello Controller";
    }

}
