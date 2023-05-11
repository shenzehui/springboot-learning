package org.javbaoy.emqp.config;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author szh
 * @Date 2022/6/2 20:28
 * @PackageName:org.javbaoy.emqp.config
 * @ClassName: HeaderReceiver
 * @Description: TODO
 * @Version 1.0
 */
@Component
public class HeaderReceiver {

    @RabbitListener(queues = "queue-age")
    public void header1(String msg){
        System.out.println("queue:msg = " + msg);
    }

    @RabbitListener(queues = "queue-name")
    public void header2(String msg){
        System.out.println("queue-name = " + msg);
    }
}
