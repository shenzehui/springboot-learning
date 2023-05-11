package org.javaboy.security;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class ApplicationTests {

    @Test
    void contextLoads() {
        for (int i = 0; i < 10; i++) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String pwd = encoder.encode("123");
            System.out.println("pwd = " + pwd);
        }
    }

}
