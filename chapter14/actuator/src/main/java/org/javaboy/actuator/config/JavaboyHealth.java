package org.javaboy.actuator.config;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * @Author szh
 * @Date 2022/6/1 16:40
 * @PackageName:org.javaboy.actuator.config
 * @ClassName: JavaboyHealth
 * @Description: 自定义 健康指示器（一般不需要）
 * @Version 1.0
 */
@Component
public class JavaboyHealth implements HealthIndicator {
    @Override
    public Health health() {
//        return Health.status("FATAL").withDetail("msg","发现严重问题").build();
        return Health.up().withDetail("msg","一切正常...").build();
    }
}
