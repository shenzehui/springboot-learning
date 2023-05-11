package org.javaboy.shiro1.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @Author szh
 * @Date 2022/5/17 9:59
 * @PackageName:org.javaboy.shiro1.realm
 * @ClassName: MyRealm
 * @Description: 认证和授权
 * @Version 1.0
 */
public class MyRealm extends AuthorizingRealm {

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 定义用户
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        if ("javaboy".equals(username)) {
            return new SimpleAuthenticationInfo(username, "123", getName());
        }
        return null;
    }
}
