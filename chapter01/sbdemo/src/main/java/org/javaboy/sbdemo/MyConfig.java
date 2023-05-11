package org.javaboy.sbdemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author szh
 */
@Configuration
//@Component
public class MyConfig {

    @Bean
    Author author() {
        return new Author();
    }

    @Bean
    Book book() {
        return new Book(author());
    }

}
