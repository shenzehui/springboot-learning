package org.javaboy.authserver;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class AuthServerApplicationTests {

    @Test
    void contextLoads() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String encode = encoder.encode("123");
        System.out.println(encode);
    }

}
