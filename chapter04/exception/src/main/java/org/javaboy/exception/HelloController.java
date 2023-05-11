package org.javaboy.exception;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author szh
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        int i = 1/0;
        return "hello";
    }
}
