package org.javaboy.jpamutil.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * @author szh
 */
@Configuration
@EnableJpaRepositories(basePackages = "org.javaboy.jpamutil.dao1", entityManagerFactoryRef = "localContainerEntityManagerFactoryBean1", transactionManagerRef = "platformTransactionManager")
public class JpaConfigOne {
    @Autowired
    @Qualifier("dsone")
    DataSource ds;

    @Autowired
    JpaProperties properties;

    @Bean
    @Primary
    LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean1(EntityManagerFactoryBuilder builder) {
        return builder.dataSource(ds).packages("org.javaboy.jpamutil.model") //指定类的包名
                .properties(properties.getProperties()).persistenceUnit("pu1").build();
    }

    @Bean
    PlatformTransactionManager platformTransactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(localContainerEntityManagerFactoryBean1(builder).getObject());
    }
}
