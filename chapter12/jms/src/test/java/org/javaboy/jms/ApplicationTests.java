package org.javaboy.jms;

import org.javaboy.jms.config.JmsComponents;
import org.javaboy.jms.model.Message;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class ApplicationTests {

    @Autowired
    JmsComponents jmsComponent;

    @Test
    void contextLoads() {
        Message message = new Message();
        message.setContent("hello");
        message.setDate(new Date());
        jmsComponent.send(message);
    }

}
