package org.javaboy.pathmapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author szh
 */
@Controller
public class HelloController {
    @GetMapping("/hello")
    public String hello2() {
        return "01";
    }
}
