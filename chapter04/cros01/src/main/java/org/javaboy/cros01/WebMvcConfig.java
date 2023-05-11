package org.javaboy.cros01;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author szh
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    /**
     * 第二种方式（推荐）
     * 配置全局跨域配置
     *
     * @param registry
     */
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")  // 请求的接口
//                .allowedHeaders("*")  // 请求的请求头
//                .allowedMethods("*")  // 请求的方法
//                .allowedOrigins("http://localhost:8081").maxAge(1800);
//    }

    /**
     * 第三种 无需再实现接口，注入到spring容器中即可
     */
    @Bean
    CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration cfg = new CorsConfiguration();

        // 配置请求地址
        cfg.addAllowedOrigin("http://localhost:8081");

        // 允许put请求
        cfg.addAllowedMethod(HttpMethod.PUT);

        // 允许所有的请求方式
        //cfg.addAllowedMethod("*");

        // 配置请求接口路径和配置
        source.registerCorsConfiguration("/**", cfg);

        // 注意这里的CorsFilter是Spring包下的，不要导错！！
        return new CorsFilter(source);
    }
}
