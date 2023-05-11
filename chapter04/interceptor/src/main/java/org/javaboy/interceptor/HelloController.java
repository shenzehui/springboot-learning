package org.javaboy.interceptor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author szh
 */
@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello1(){
        System.out.println("hello1");
        return "hello1";
    }
    @GetMapping("/hello2")
    public String hello2(){
        System.out.println("hello2");
        return "hello2";
    }
}
