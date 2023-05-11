package org.javaboy.cacheredis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author szh
 */
@SpringBootApplication
@EnableCaching //开启缓存功能 会配置好cacheManager
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
