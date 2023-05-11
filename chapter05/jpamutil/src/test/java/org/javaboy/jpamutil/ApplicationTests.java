package org.javaboy.jpamutil;

import org.javaboy.jpamutil.dao1.UserDao1;
import org.javaboy.jpamutil.dao2.UserDao2;
import org.javaboy.jpamutil.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ApplicationTests {

    @Autowired
    UserDao1 dao1;

    @Autowired
    UserDao2 dao2;
    @Test
    void contextLoads() {
        List<User> a1 = dao1.findAll();
        System.out.println("a1 = " + a1);
        List<User> a2 = dao2.findAll();
        System.out.println("a2 = " + a2);
    }

}
