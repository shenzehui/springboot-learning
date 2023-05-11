package org.javaboy.pathmapping;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author szh
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    /**
     * 路径映射，前提是页面没有渲染的数据。
     *
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/02").setViewName("02");
    }
}
