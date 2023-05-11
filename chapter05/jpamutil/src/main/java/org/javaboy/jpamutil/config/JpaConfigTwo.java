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
@EnableJpaRepositories(basePackages = "org.javaboy.jpamutil.dao2", entityManagerFactoryRef = "localContainerEntityManagerFactoryBean2", transactionManagerRef = "platformTransactionManager2")
public class JpaConfigTwo {
    @Autowired
    @Qualifier("dstwo")
    DataSource ds;

    @Autowired
    JpaProperties properties;

    @Bean
    LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean2(EntityManagerFactoryBuilder builder) {
        return builder.dataSource(ds).packages("org.javaboy.jpamutil.model").properties(properties.getProperties()).persistenceUnit("pu2").build();
    }

    @Bean
    PlatformTransactionManager platformTransactionManager2(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(localContainerEntityManagerFactoryBean2(builder).getObject());
    }
}
