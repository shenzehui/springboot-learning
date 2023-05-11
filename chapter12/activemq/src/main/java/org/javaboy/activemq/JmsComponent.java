package org.javaboy.activemq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Queue;

/**
 * @author szh
 */
@Component
public class JmsComponent {

    @Autowired
    JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    Queue queue;

    public void send(Message message) {
        jmsMessagingTemplate.convertAndSend(this.queue, message);
    }

    @JmsListener(destination = "hello.javaboy")
    public void receive(Message msg) {
        System.out.println(msg);
    }
}
