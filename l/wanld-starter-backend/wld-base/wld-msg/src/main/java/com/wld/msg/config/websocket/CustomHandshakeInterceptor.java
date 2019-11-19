package com.wld.msg.config.websocket;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.lang.Nullable;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


/**
 * websocket 握手拦截器
 * ----未实现什么有意义的事啊
 *
 * @author 王增光
 * @date 2018/8/23
 */
public class CustomHandshakeInterceptor implements HandshakeInterceptor {

    @Override
    public boolean beforeHandshake(ServerHttpRequest var1, ServerHttpResponse var2, WebSocketHandler var3, Map<String, Object> var4) throws Exception {
        ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) var1;
        HttpServletRequest httpServletRequest = servletRequest.getServletRequest();
        String token = httpServletRequest.getParameter("token");

        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest var1, ServerHttpResponse var2, WebSocketHandler var3, @Nullable Exception var4) {

    }

}