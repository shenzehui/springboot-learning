package org.javaboy.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

/**
 * @author szh
 */
@Configuration
public class WebMvcConfig {
    /**
     * 三种方式
     */
//    @Bean
//    GsonBuilder gsonBuilder(){
//        GsonBuilder gsonBuilder = new GsonBuilder();
//        gsonBuilder.setDateFormat("yyyy/MM/HH/mm/ss");
//        return gsonBuilder;
//    }

//    @Bean
//    Gson gson(){
//        GsonBuilder gsonBuilder = new GsonBuilder();
//        Gson gson = gsonBuilder.setDateFormat("yyyy:MM:HH").create();
//        return gson;
//    }
    @Bean
    GsonHttpMessageConverter gsonHttpMessageConverter() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("yyyy/MM/HH/mm/ss");
        GsonHttpMessageConverter converter = new GsonHttpMessageConverter();
        converter.setGson(gsonBuilder.create());
        return converter;
    }
}
