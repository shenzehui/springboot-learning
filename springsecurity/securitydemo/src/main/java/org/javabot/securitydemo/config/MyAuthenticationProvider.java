package org.javabot.securitydemo.config;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author szh
 * @Date 2022/5/20 15:48
 * @PackageName:org.javabot.securitydemo.config
 * @ClassName: MyAuthenticationProvider
 * @Description: 自定义AuthenticationProvider 目的：在校验密码之前能够校验验证码
 * @Version 1.0
 */
/*DaoAuthenticationProvider 校验密码使用的*/
public class MyAuthenticationProvider extends DaoAuthenticationProvider {
    /*校验密码*/
    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
       if(!((MyWebAuthenticationDetails) authentication.getDetails()).isPassed()) {
           throw new AuthenticationServiceException("验证码错误");
       }

        /*之前校验验证码*/

        /*获取当前请求*/
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        String code = request.getParameter("code");
//        String vf = (String) request.getSession().getAttribute("vf");
//        if(code == null || vf == null || !code.equals(vf)){
//            /*报错*/
//            throw new AuthenticationServiceException("验证码错误");
//        }
        /*否则校验密码*/
        super.additionalAuthenticationChecks(userDetails, authentication);
    }
}
