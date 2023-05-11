package org.javaboy.idempontent.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.javaboy.idempontent.exception.IdempotentException;
import org.javaboy.idempontent.token.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * AOP实现
 *
 * @author szh
 */
@Component
@Aspect
public class IdempotentAspect {

    @Autowired
    TokenService tokenService;

    /**
     * 定义切面，拦截注解
     */
    @Pointcut("@annotation(org.javaboy.idempontent.anno.AutoIdempotent)")
    public void pc1() {

    }

    @Before("pc1()")
    public void before() throws IdempotentException {
        // 获取请求对象
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        try {
            tokenService.checkToken(request);
        } catch (IdempotentException e) {
            throw e;
        }
    }
}
