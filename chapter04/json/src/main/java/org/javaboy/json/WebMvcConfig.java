package org.javaboy.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

/**
 * @author szh
 */
@Configuration
public class WebMvcConfig {
    /**
     * jackson ：提供ObjectMapper
     *
     * @return
     */
    @Bean
    ObjectMapper objectMapper() {
        ObjectMapper om = new ObjectMapper();
        om.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        return om;
    }
}
