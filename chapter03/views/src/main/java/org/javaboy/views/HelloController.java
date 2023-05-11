package org.javaboy.views;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author szh
 */
@Controller
public class HelloController {

    @GetMapping("/{path}")
    public String index(@PathVariable("path") String path) {
        return path;
    }

}
