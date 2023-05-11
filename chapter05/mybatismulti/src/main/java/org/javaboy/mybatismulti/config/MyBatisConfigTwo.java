package org.javaboy.mybatismulti.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author szh
 */
@Configuration
@MapperScan(basePackages = "org.javaboy.mybatismulti.mapper2", sqlSessionFactoryRef = "sqlSessionFactory2", sqlSessionTemplateRef = "sqlSessionTemplate2")
public class MyBatisConfigTwo {

    @Autowired
    @Qualifier("ds2")
    DataSource ds;

    @Bean
    SqlSessionFactory sqlSessionFactory2() {
        SqlSessionFactory sqlSessionFactory = null;
        try {
            SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
            bean.setDataSource(ds);
            sqlSessionFactory = bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sqlSessionFactory;
    }

    @Bean
    SqlSessionTemplate sqlSessionTemplate2() {
        return new SqlSessionTemplate(sqlSessionFactory2());
    }

}
