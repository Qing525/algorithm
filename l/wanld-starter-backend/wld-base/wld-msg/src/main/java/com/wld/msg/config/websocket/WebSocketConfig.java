package com.wld.msg.config.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;


/**
 * spring bootwebsocket配置
 *
 * @author 王增光
 * @date 2019/8/14
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
        stompEndpointRegistry.addEndpoint("/socket")
                .addInterceptors(new CustomHandshakeInterceptor())
                .setHandshakeHandler(new CustomHandshakeHandler())
                .setAllowedOrigins("*")
                //使用sockjs客户端
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic", "/sequence");

        registry.setApplicationDestinationPrefixes("/app");
    }
}