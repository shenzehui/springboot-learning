package org.javaboy.config;

import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.javaboy.interceptor.MyInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;

/**
 * 这个配置类相当于spring-server.xml
 *
 * @author szh
 */
@Configuration
@ComponentScan(basePackages = "org.javaboy", useDefaultFilters = false, includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Controller.class),
        /**
         * 去扫 SpringConfig 上的注解
         */
        @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)})
public class SpringMvcConfig extends WebMvcConfigurationSupport {

    /**
     * 配置静态资源路径
     *
     * @param registry
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 配置路径解析
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

    /**
     * 配置视图解析器
     *
     * @param registry
     */
    @Override
    protected void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/jsp/", ".jsp");
    }

    /**
     * 添加路径，访问/hello3 跳转到 hello.html页面
     *
     * @param registry
     */
    @Override
    protected void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/hello3", "hello");
    }

    /**
     * 配置拦截器
     *
     * @param registry
     */
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        // 一个*代表单层路径，两个*代表任意层数的路径
        registry.addInterceptor(myInterceptor()).addPathPatterns("/**");
    }

    @Bean
    MyInterceptor myInterceptor() {
        return new MyInterceptor();
    }

    /**
     * 配置 json 格式转换器 返回的 List 自动转换为 JSON 字符串
     */
    @Override
    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        FastJsonConfig config = new FastJsonConfig();
        config.setDateFormat("yyyy-MM-dd");
        converter.setFastJsonConfig(config);
        converters.add(converter);
    }

}
