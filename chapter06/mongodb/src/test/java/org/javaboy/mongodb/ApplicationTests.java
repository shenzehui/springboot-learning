package org.javaboy.mongodb;

import org.javaboy.mongodb.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

@SpringBootTest
class ApplicationTests {

    @Autowired
    MongoTemplate mongoTemplate;

    @Test
    void contextLoads() {
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setId((long) i);
            user.setUsername("javaboy" + i);
            user.setAddress("www.org.javaboy" + i);
            mongoTemplate.insert(user);
        }
    }

    @Test
    void test1() {
        List<User> list = mongoTemplate.findAll(User.class);
        System.out.println("list = " + list);
    }

    @Test
    void test2() {
        User user = new User();
        user.setId(1L);
        mongoTemplate.remove(user);
    }

}
