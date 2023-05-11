package org.javaboy.securitydy.config;

import org.javaboy.securitydy.bean.Menu;
import org.javaboy.securitydy.bean.Role;
import org.javaboy.securitydy.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/**
 * 配置过滤器
 * 作用：根据请求的地址分析出来对应的角色（可能会有多个）
 *
 * @author szh
 */
@Component
public class MyFilter implements FilterInvocationSecurityMetadataSource {

    @Autowired
    MenuService menuService;

    /**
     * 路径匹配符
     */
    AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        // 获取请求地址
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        List<Menu> allMenus = menuService.getAllMenus();
        for (Menu menu : allMenus) {
            // 数据库中定义规则和请求地址是否匹配
            if (pathMatcher.match(menu.getPattern(), requestUrl)) {
                // 获取请求地址的对应角色
                List<Role> roles = menu.getRoles();
                // 参数是可变数组返回当前路径匹配的角色名称数组
                String[] rolesStr = new String[roles.size()];
                for (int i = 0; i < rolesStr.length; i++) {
                    rolesStr[i] = roles.get(i).getName();
                }
                // 返回请求接口地址对应的角色信息
                return SecurityConfig.createList(rolesStr);
            }
        }
        // 没有与之相匹配的路径
        return SecurityConfig.createList("ROLE_login");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
