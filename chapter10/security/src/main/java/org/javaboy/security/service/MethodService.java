package org.javaboy.security.service;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

/**
 * 对方法访问权限的指定
 */
@Service
public class MethodService {

    /**
     * 指定只有admin角色才可以执行该方法
     *
     * @return
     */
    @PreAuthorize("hasRole('admin')")
    public String admin() {
        return "hello admin";
    }

    /**
     * 与  @PreAuthorize效果相同 不过要加 ROLE_前缀
     */
    @Secured("ROLE_user")
    public String user() {
        return "hello user";
    }

    @PreAuthorize("hasAnyRole('admin','user')")
    public String hello() {
        return "hello hello";
    }


}
