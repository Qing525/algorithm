package com.wld.msg.config.websocket;

import java.security.Principal;
import java.util.Objects;

/**
 * 未登录用户访问websocket使用的principal
 *
 * @author 王增光
 * @date 2019/8/14
 */
public class AnonymousPrincipal implements Principal {

    private String name;

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object another) {
        if (!(another instanceof Principal)) {
            return false;
        }
        Principal principal = (Principal) another;
        return principal.getName() == this.name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
