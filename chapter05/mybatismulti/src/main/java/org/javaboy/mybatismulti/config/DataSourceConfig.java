package org.javaboy.mybatismulti.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author szh
 */
@Configuration
public class DataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.one")
    DataSource ds1() {
        return new HikariDataSource();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.two")
    DataSource ds2() {
        return new HikariDataSource();
    }

}
