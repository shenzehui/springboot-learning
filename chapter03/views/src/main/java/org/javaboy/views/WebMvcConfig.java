package org.javaboy.views;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author szh
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    /**
     * 设置后先跟 jsp 匹配，会查找是否有 jsp 页面，最终会显示出 jsp 页面
     */
    @Override
    protected void configureViewResolvers(ViewResolverRegistry registry) {
        // 设置jsp具有检查视图的能力
        registry.jsp("/", ".jsp").viewClass(HandleInternalResourceViewExists.class);
        // 设置优先级 设置成最高，访问freemarker时，不管页面是否有总是加到候选中去，导致无法访问到
        registry.order(1);
    }
}
