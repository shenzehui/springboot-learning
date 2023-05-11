package org.javaboy.jsp;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author szh
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    /**
     * 视图解析器Java配置
     */
    @Override
    protected void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/jsp/", ".jsp");
    }
}
