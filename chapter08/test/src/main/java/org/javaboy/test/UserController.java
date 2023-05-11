package org.javaboy.test;

import org.javaboy.test.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author szh
 */
@RestController
public class UserController {

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable Long id) {
        User user = new User();
        user.setId(id);
        user.setUsername("javaboy");
        return user;
    }

}
