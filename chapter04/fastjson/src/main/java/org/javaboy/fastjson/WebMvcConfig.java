package org.javaboy.fastjson;

import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author szh
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 第一种配置方式
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setCharset(Charset.forName("UTF-8"));
        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
        converter.setFastJsonConfig(fastJsonConfig);
        converter.setDefaultCharset(Charset.forName("UTF-8"));
        // 放到集合中去
        converters.add(converter);
    }

    /**
     * 第二种配置方式
     */
//    @Bean
//    FastJsonHttpMessageConverter fastJsonHttpMessageConverter(){
//        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
//        FastJsonConfig fastJsonConfig = new FastJsonConfig();
//        fastJsonConfig.setCharset(Charset.forName("UTF-8"));
//        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
//        converter.setFastJsonConfig(fastJsonConfig);
//        converter.setDefaultCharset(Charset.forName("UTF-8"));
//        return converter;
//    }

}
