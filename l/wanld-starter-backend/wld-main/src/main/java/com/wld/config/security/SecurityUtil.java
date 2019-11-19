package com.wld.config.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

/**
 * 用于获得当前登录用户信息
 *
 * @author 王增光
 */
public class SecurityUtil {

    public static Optional<SecurityUserDetails> getCurrentUser() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            return Optional.empty();
        }

        SecurityUserDetails user = null;
        if (authentication.getPrincipal() instanceof SecurityUserDetails) {
            user = (SecurityUserDetails) authentication.getPrincipal();
        }
        return Optional.ofNullable(user);
    }

    /**
     * 获得当前登录用户的id，如果当前用户未登录，throw UsernameNotFoundException;
     *
     * @return 用户id
     */
    public static Long getCurrentUserId() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            throw new UsernameNotFoundException("当前用户未登录，错误调用SecurityUtil.getCurrentUserId");
        }

        SecurityUserDetails springSecurityUser = (SecurityUserDetails) authentication.getPrincipal();
        if (springSecurityUser == null) {
            throw new UsernameNotFoundException("当前用户未登录，错误调用SecurityUtil.getCurrentUserId");
        }
        return springSecurityUser.getUserId();
    }

    public static Optional<String> getCurrentUsername() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            return Optional.empty();
        }

        String username = null;
        if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
            username = springSecurityUser.getUsername();
        } else if (authentication.getPrincipal() instanceof String) {
            username = (String) authentication.getPrincipal();
        }
        return Optional.ofNullable(username);
    }
}
