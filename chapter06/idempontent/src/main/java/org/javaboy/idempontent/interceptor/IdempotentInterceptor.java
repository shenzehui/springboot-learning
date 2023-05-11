package org.javaboy.idempontent.interceptor;

import org.javaboy.idempontent.anno.AutoIdempotent;
import org.javaboy.idempontent.exception.IdempotentException;
import org.javaboy.idempontent.token.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 拦截器实现
 *
 * @author szh
 */
//@Component
public class IdempotentInterceptor implements HandlerInterceptor {

    // @Autowired
    TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // handler表示被拦截的方法，就是HandlerMethod，如果不是就不是被拦截下来的方法，放行
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        // 方法被拦截
        Method method = ((HandlerMethod) handler).getMethod();
        AutoIdempotent idempotent = method.getAnnotation(AutoIdempotent.class);
        // 有注解，进行幂等性的处理
        if (idempotent != null) {
            try {
                return tokenService.checkToken(request);
            } catch (IdempotentException e) {
                throw e;
            }
        }
        return true;
    }
}
