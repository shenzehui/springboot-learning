package org.javaboy.jdbctemplatemutl.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * @author szh
 */
@Configuration
public class JdbcTemplateConfig {

    /**
     * @Qualifier 指定 Spring 容器中的对象
     */
    @Bean
    JdbcTemplate jdbcTemplateOne(@Qualifier("dsOne") DataSource ds) {
        return new JdbcTemplate(ds);
    }

    @Bean
    JdbcTemplate jdbcTemplateTwo(@Qualifier("dsTwo") DataSource ds) {
        return new JdbcTemplate(ds);
    }

}
