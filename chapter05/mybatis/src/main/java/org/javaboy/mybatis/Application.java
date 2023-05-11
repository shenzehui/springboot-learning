package org.javaboy.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author szh
 */
@SpringBootApplication
@MapperScan(basePackages = "org.javaboy.mybatis.mapper")  // 扫描接口所在位置
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
