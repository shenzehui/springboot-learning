package org.javaboy.controller;

import org.javaboy.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author szh
 */
@RestController
public class HelloController {

    @Autowired
    HelloService helloService;

    /**
     * produces 指定对应的字符集
     */
    @GetMapping(value = "/hello", produces = "text/html;charset=utf-8")
    public String hello() {
        return helloService.sayHello();
    }

}
