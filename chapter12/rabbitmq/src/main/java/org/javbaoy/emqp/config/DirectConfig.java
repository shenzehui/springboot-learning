package org.javbaoy.emqp.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author szh
 * @Date 2022/5/18 12:09
 * @PackageName:org.javbaoy.emqp.config
 * @ClassName: DirectConfig
 * @Description: TODO
 * @Version 1.0
 */
@Configuration
public class DirectConfig {

    @Bean
    Queue directQueue() {
        return new Queue("javaboy-queue");
    }

    /**
     * 如果用了 direct 模式，下面两个 bean 可以省略
     */
//    @Bean
//    DirectExchange directExchange(){
//        /**
//         * 参数说明：
//         *   name:交换机名称
//         *   durable:重启后是否有效
//         *  autoDelete：长期不使用是否自动删除
//         */
//        return new DirectExchange("javaboy-queue",true,false);
//    }
//
    /**
     *  绑定
     */
//    @Bean
//    Binding directBinding(){
//        return BindingBuilder.bind(directQueue()).to(directExchange()).with("direct");
//    }
}
