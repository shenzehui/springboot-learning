package org.javaboy.jdbctemplatemutl;

import org.javaboy.jdbctemplatemutl.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class ApplicationTests {

    @Autowired
    @Qualifier("jdbcTemplateOne")
    JdbcTemplate jdbcTemplateOne;

    @Resource(name = "jdbcTemplateTwo")
    JdbcTemplate jdbcTemplateTwo;

    @Test
    public void contextLoads() {
        List<User> list1 = jdbcTemplateOne.query("select * from user", new BeanPropertyRowMapper<>(User.class));
        List<User> list2 = jdbcTemplateTwo.query("select * from user", new BeanPropertyRowMapper<>(User.class));
        System.out.println(list1);
        System.out.println(list2);
    }
}
