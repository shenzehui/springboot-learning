package org.javaboy.chat01.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * @Author szh
 * @Date 2022/5/17 14:00
 * @PackageName:org.javaboy.chat01.config
 * @ClassName: WebSocketConfig
 * @Description: TODO
 * @Version 1.0
 */
@Configuration
@EnableWebSocketMessageBroker // 开启websocket的消息代理
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    /**
     * 注册端点（前端会连接这个地址）
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 定义了一个前缀为 chart 的 Endpoint，并提供了浏览器支持
        // 定义建立 websocket 的连接地址
        registry.addEndpoint("/chat").withSockJS();
    }

    /**
     * 配置消息代理
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 设置消息代理的前缀，如果发送的消息是"/topic" 就会把这个消息转发给消息代理，再把消息代理转发给所有连接的客户端
        registry.enableSimpleBroker("/topic");
        // 自定义消息处理接口
//        registry.setApplicationDestinationPrefixes("/app");
    }
}
