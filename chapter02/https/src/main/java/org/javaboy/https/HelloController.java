package org.javaboy.https;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author szh
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello https";
    }
}
