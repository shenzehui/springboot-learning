package org.javbaoy.emqp.config;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author szh
 * @Date 2022/5/18 12:13
 * @PackageName:org.javbaoy.emqp.config
 * @ClassName: DirectReceiver
 * @Description: TODO
 * @Version 1.0
 */
@Component
public class DirectReceiver {

    @RabbitListener(queues = "javaboy-queue")
    public void handler(String msg) {
        System.out.println("msg = " + msg);
    }
}
