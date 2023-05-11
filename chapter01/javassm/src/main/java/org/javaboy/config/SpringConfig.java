package org.javaboy.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

/**
 * 这个配置类相当于 applicationContext.xml
 *
 * @author szh
 */
@Configuration
@ComponentScan(basePackages = "org.javaboy", useDefaultFilters = true, excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Controller.class)})
public class SpringConfig {
}
