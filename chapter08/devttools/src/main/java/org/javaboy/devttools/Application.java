package org.javaboy.devttools;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author szh
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        // 第二种方法：禁用自动重启
//        System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(Application.class, args);
    }

}
