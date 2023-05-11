package org.javbaoy.emqp.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author szh
 * @Date 2022/6/2 20:24
 * @PackageName:org.javbaoy.emqp.config
 * @ClassName: HeadConfig
 * @Description: TODO
 * @Version 1.0
 */
@Configuration
public class HeadConfig {
    @Bean
    Queue queueAge() {
        return new Queue("queue-age");
    }

    @Bean
    Queue queueName() {
        return new Queue("queue-name");
    }

    @Bean
    HeadersExchange headersExchange() {
        return new HeadersExchange("javaboy-header", true, false);
    }

    @Bean
    Binding bindingAge() {
        Map<String, Object> map = new HashMap<>();
        map.put("age", 99);
        return BindingBuilder.bind(queueAge()).to(headersExchange()).whereAny(map).match();
    }

    @Bean
    Binding bindingName() {
        // 存在 name 即可
        return BindingBuilder.bind(queueName()).to(headersExchange()).whereAny("name").exist();
    }
}
