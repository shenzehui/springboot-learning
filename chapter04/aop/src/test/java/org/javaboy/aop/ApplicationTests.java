package org.javaboy.aop;

import org.javaboy.aop.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ApplicationTests {

    @Autowired
    private UserService userService;
    @Test
    public void contextLoads() {
        userService.getUserById(99);
    }

}
