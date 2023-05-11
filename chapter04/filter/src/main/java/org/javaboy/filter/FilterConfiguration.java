package org.javaboy.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * @author szh
 */
@Configuration
public class FilterConfiguration {

    /**
     * 通过配置类返回 FilterRegistrationBean来设置Filter的优先级和拦截路径 ，两者都可以配置
     */
    @Bean
    FilterRegistrationBean<MyFilter04> filter04FilterRegistrationBean04() {
        FilterRegistrationBean<MyFilter04> bean = new FilterRegistrationBean();
        bean.setOrder(90);
        bean.setFilter(new MyFilter04());
        bean.setUrlPatterns(Arrays.asList("/*"));
        return bean;
    }

    @Bean
    FilterRegistrationBean<MyFilter05> filter05FilterRegistrationBean05() {
        FilterRegistrationBean<MyFilter05> bean = new FilterRegistrationBean();
        bean.setOrder(89);
        bean.setFilter(new MyFilter05());
        bean.setUrlPatterns(Arrays.asList("/*"));
        return bean;
    }
}
