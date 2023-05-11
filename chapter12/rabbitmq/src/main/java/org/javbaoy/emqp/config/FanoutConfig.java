package org.javbaoy.emqp.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author szh
 * @Date 2022/6/2 19:53
 * @PackageName:org.javbaoy.emqp.config
 * @ClassName: FanoutConfig
 * @Description: TODO
 * @Version 1.0
 */
@Configuration
public class FanoutConfig {
    @Bean
    Queue queueOne() {
        return new Queue("queue-one");
    }

    @Bean
    Queue queueTwo() {
        return new Queue("queue-two");
    }

    /**
     * 交换机
     */
    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange("javaboy-fanout", true, false);
    }

    /**
     * 绑定队列到同一个交换机
     */
    @Bean
    Binding bindOne() {
        return BindingBuilder.bind(queueOne()).to(fanoutExchange());
    }

    @Bean
    Binding bindTwo() {
        return BindingBuilder.bind(queueTwo()).to(fanoutExchange());
    }


}
