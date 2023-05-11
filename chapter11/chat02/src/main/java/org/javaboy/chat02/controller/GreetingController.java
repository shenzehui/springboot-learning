package org.javaboy.chat02.controller;

import org.javaboy.chat02.model.Chat;
import org.javaboy.chat02.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;

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

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/hello") // 接收消息地址  前端以JSON格式传参
    // 转发消息地址，转发的参数就是方法的返回值：前端页面会有监听地址，前端只要监听到这个地址，就都能收到消息
//    @SendTo("/topic/greetings")
    public Message greeting(Message message) {
        simpMessagingTemplate.convertAndSend("/topic/greetings", message);
        System.out.println("message = " + message);
        return message;
    }

    /**
     * Principal:当前用户信息
     */
    @MessageMapping("/online_chat")
    public void chat(Principal principal, Chat chat) {
        // 从服务端获取消息的发送者
        String from = principal.getName();
        chat.setFrom(from);
        /**
         * 参数说明：
         * user:            发送给谁
         * destination：    前端监听地址  前端这里需要加 /user
         * payload:         发送的消息(前端监听器监听到的内容) 对象
         */
        simpMessagingTemplate.convertAndSendToUser(chat.getTo(), "/queue/chat", chat);
    }
}
