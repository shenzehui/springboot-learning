package org.javaboy.idempontent.config;

import org.javaboy.idempontent.interceptor.IdempotentInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器注册
 *
 * @author szh
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
//
//    @Autowired
//    IdempotentInterceptor idempotentInterceptor;
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(idempotentInterceptor).addPathPatterns("/**");
//    }
}
