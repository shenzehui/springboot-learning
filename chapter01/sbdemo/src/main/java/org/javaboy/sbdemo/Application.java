package org.javaboy.sbdemo;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author szh
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
//        SpringApplication.run(Application.class, args);

        // 关闭Banner操作
        SpringApplicationBuilder builder = new SpringApplicationBuilder(Application.class);
        SpringApplication build = builder.build();
        build.setBannerMode(Banner.Mode.OFF);
        build.run(args);
    }
}
