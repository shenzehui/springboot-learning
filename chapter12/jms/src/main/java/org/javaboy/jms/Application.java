package org.javaboy.jms;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.jms.Queue;

/**
 * @author szh
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * 消息队列
     * jms包：接口规范 activemq 来实现
     */
    @Bean
    Queue queue() {
        return new ActiveMQQueue("javaboy-queue");
    }

}
