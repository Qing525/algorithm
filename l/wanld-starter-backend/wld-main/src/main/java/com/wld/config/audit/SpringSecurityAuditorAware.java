package com.wld.config.audit;

import com.wld.config.security.SecurityUserDetails;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;


/***
 * 启用jap审计配置，结合spring secutiry 实现当前用户id的获得。
 * @author 王增光
 * @date 2019/9/5
 */

class SpringSecurityAuditorAware implements AuditorAware<Long> {
    @Override
    public Optional<Long> getCurrentAuditor() {

        Optional<Long> user = Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .filter(Authentication::isAuthenticated)
                .map(Authentication::getPrincipal)
                .map(obj -> (SecurityUserDetails) obj)
                .map(SecurityUserDetails::getUserId);
        return user;
    }
}