package com.wld.msg.config.websocket;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;
import java.util.UUID;

/**
 * 配置WebSocketConfig的setHandshakeHandler。自定义handler，以处理未登录用户访问需要登录的地址。
 *
 * @author 王增光
 * @date 2019/8/14
 */
public class CustomHandshakeHandler extends DefaultHandshakeHandler {

    @Override
    protected Principal determineUser(ServerHttpRequest request,
                                      WebSocketHandler wsHandler, Map<String, Object> attributes) {
        Principal principal = request.getPrincipal();

        if (principal == null) {
            principal = new AnonymousPrincipal();
            String uniqueName = "anonymous" + UUID.randomUUID().toString();
            ((AnonymousPrincipal) principal).setName(uniqueName);
        }
        return principal;
    }
}

