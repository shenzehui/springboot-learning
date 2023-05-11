package org.javbaoy.emqp.config;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author szh
 * @Date 2022/6/2 20:18
 * @PackageName:org.javbaoy.emqp.config
 * @ClassName: TopicReceiver
 * @Description: TODO
 * @Version 1.0
 */
@Component
public class TopicReceiver {

    @RabbitListener(queues = "phone")
    public void handle1(String msg){
        System.out.println("phone:msg = " + msg);
    }

    @RabbitListener(queues = "xiaomi")
    public void handle2(String msg){
        System.out.println("xiaomi:msg = " + msg);
    }
    @RabbitListener(queues = "huawei")
    public void handle3(String msg){
        System.out.println("huawei:msg = " + msg);
    }

}
