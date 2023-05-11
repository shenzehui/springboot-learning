package org.javaboy.filter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author szh
 */
@SpringBootApplication
@ServletComponentScan("org.javaboy.filter")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
