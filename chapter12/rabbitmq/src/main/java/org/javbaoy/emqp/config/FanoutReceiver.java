package org.javbaoy.emqp.config;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author szh
 * @Date 2022/6/2 20:01
 * @PackageName:org.javbaoy.emqp.config
 * @ClassName: FanoutReceiver
 * @Description: 监听器
 * @Version 1.0
 */
@Component
public class FanoutReceiver {
    @RabbitListener(queues = "queue-one")
    public void handle1(String msg) {
        System.out.println("handle1:msg = " + msg);
    }

    @RabbitListener(queues = "queue-two")
    public void handle2(String msg) {
        System.out.println("handle2:msg = " + msg);
    }
}
