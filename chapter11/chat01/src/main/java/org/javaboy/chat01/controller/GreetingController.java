package org.javaboy.chat01.controller;

import org.javaboy.chat01.model.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * @Author szh
 * @Date 2022/5/17 14:09
 * @PackageName:org.javaboy.chat01.controller
 * @ClassName: GreetingController
 * @Description: TODO
 * @Version 1.0
 */
@Controller
public class GreetingController {

    @MessageMapping("/hello") // 接收消息地址  前端以 JSON 格式传参
    @SendTo("/topic/greetings") // 转发消息地址，转发的参数就是方法的返回值：前端页面会有监听地址，前端只要监听到这个地址，就都能收到消息
    public Message greeting(Message message) {
        System.out.println("message = " + message);
        return message;
    }
}
