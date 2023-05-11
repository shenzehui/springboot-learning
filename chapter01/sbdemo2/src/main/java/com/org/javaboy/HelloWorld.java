package com.org.javaboy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 1.在线创建 https://start.spring.io
 * 2.IDEA 创建
 * 3.Maven 改造
 * <p>
 * 也可以使用  阿里云 提供的 start 站点
 * <p>
 * https://start.aliyun.com
 *
 * @author szh
 */
@RestController
@EnableAutoConfiguration
public class HelloWorld {

    @RequestMapping("/")
    String home() {
        return "Hello World";
    }

    public static void main(String[] args) {
        SpringApplication.run(HelloWorld.class, args);
    }

}
