package org.javaboy.cros01;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author szh
 */
@RestController
/**
 * value：允许跨域的请求
 * maxAge 准备响应前的 缓存持续的 最大时间
 */
//第一种方式解决跨域
//@CrossOrigin(value = "http://localhost:8081",maxAge = 1800)
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello cros";
    }

    @PutMapping("/hello")
    public String hello2() {
        return "hello cros put";
    }
}
