package org.javaboy.banner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author szh
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        // 两种方式关闭banner
//        SpringApplication app = new SpringApplication(Application.class);
//        app.setBannerMode(Banner.Mode.OFF);
//        app.run(args);
        SpringApplication.run(Application.class, args);
    }

}
