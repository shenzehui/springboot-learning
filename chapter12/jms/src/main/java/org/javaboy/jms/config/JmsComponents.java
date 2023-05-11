package org.javaboy.jms.config;

import org.javaboy.jms.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Queue;

/**
 * @Author szh
 * @Date 2022/5/17 17:23
 * @PackageName:org.javaboy.config
 * @ClassName: JmsComponents
 * @Description: TODO
 * @Version 1.0
 */
@Component
public class JmsComponents {
    /**
     * 注入消息发送模板
     */
    @Autowired
    JmsMessagingTemplate jmsMessagingTemplate;

    /**
     * 注入定义的队列
     */
    @Autowired
    Queue queue;

    /**
     * 接收和发送应该存在两个项目中
     */
    public void send(Message message) {
        /**
         * 参数说明：
         *  destination：发送的目的地：消息队列
         *  payload：    荷载 发送的内容，消息本身
         */
        jmsMessagingTemplate.convertAndSend(queue, message);
    }

    /**
     * 监听消息队列，并接收
     */
    @JmsListener(destination = "javaboy-queue")
    public void receive(Message message) {
        System.out.println("message = " + message);
    }

}
