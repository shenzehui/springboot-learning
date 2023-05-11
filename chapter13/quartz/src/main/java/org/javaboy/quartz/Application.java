package org.javaboy.quartz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author szh
 */
@SpringBootApplication
// quartz这里无需配置
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
