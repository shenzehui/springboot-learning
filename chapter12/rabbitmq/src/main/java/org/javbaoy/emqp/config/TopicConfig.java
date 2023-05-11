package org.javbaoy.emqp.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author szh
 * @Date 2022/6/2 20:13
 * @PackageName:org.javbaoy.emqp.config
 * @ClassName: TopicConfig
 * @Description: TODO
 * @Version 1.0
 */
@Configuration
public class TopicConfig {
    @Bean
    Queue xiaomi() {
        return new Queue("xiaomi");
    }

    @Bean
    Queue huawei() {
        return new Queue("huawei");
    }

    @Bean
    Queue phone() {
        return new Queue("phone");
    }

    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange("javaboy-topic", true, false);
    }

    @Bean
    Binding xiaomiBinding() {
        // 定义 routingkey 只要以 xiaomi 开头即可收到消息
        return BindingBuilder.bind(xiaomi()).to(topicExchange()).with("xiaomi.#");
    }

    @Bean
    Binding huaweiBinding() {
        return BindingBuilder.bind(huawei()).to(topicExchange()).with("hauwei.#");
    }

    @Bean
    Binding phoneBinding() {
        return BindingBuilder.bind(phone()).to(topicExchange()).with("#.phone.#");
    }

}
