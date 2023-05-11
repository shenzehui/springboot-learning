package org.javaboy.cache_ehcache;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {

    @Autowired
    UserService userService;

    @Test
    void contextLoads() {
        User user1 = userService.getUserById(100L);
        User user2 = userService.getUserById(100L);
        System.out.println("user1 = " + user1);
        System.out.println("user2 = " + user2);
    }

}
