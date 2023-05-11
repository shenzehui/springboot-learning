package org.javaboy.securitydy.config;


import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * 根据校验和匹配用户的角色和请求地址的角色
 *
 * @author szh
 */
@Component
public class MyAccessDecisionManager implements AccessDecisionManager {
    /**
     * @param authentication   登录用户信息 ：用户所对应的角色
     * @param object           FilterInvocation 对象
     * @param configAttributes 接口返回的角色信息：作用：获取用户的所有角色
     * @throws AccessDeniedException
     * @throws InsufficientAuthenticationException
     */
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        for (ConfigAttribute attribute : configAttributes) {
            if ("ROLE_login".equals(attribute.getAttribute())) {
                // 认为这个请求是登录之后就能访问的，请求没有对应的角色，不受限
                if (authentication instanceof AnonymousAuthenticationToken) {
                    // 匿名登录
                    throw new AccessDeniedException("非法请求!");
                } else {
                    return;
                }
            }
            // 获取用户所具备的角色（所有的）
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for (GrantedAuthority authority : authorities) {
                // 用户角色（一个或多个）有与接口访问所需角色（一个或多个）相匹配的就 return，否则抛出异常 ,可以有另外的策略
                if (authority.getAuthority().equals(attribute.getAttribute())) {
                    return;
                }
            }
        }
        throw new AccessDeniedException("非法请求!");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
