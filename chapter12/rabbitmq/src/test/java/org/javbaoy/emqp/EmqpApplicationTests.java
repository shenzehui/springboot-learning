package org.javbaoy.emqp;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EmqpApplicationTests {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    void contextLoads() {
        /*
        * routingkey：队列名字
        * object:发送内容
        * */
        rabbitTemplate.convertAndSend("hello-queue", "hellojavaboy");
    }

    @Test
    void test1(){
        /*这里routingkey还没有用*/
        rabbitTemplate.convertAndSend("javaboy-fanout", null, "hellojavaboy");
    }

    @Test
    void test2(){
        rabbitTemplate.convertAndSend("javaboy-topic", "xiaomi.phone", "小米手机");
    }

    @Test
    void test3(){
        /*这里的age 对应的 value 是 99 才能够发送成功 ，name 则无所谓 */
        Message nameMsg = MessageBuilder.withBody("hello szh,Good Morning!".getBytes()).setHeader("age", 99).build();
        rabbitTemplate.convertAndSend("javaboy-header", null, nameMsg);
    }

}
