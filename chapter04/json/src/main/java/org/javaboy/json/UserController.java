package org.javaboy.json;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author szh
 */
@RestController
public class UserController {
    @GetMapping("/user")
    public User getUserById() {
        User user = new User();
        user.setUsername("javaboy");
        user.setBirthday(new Date());
        user.setAddress("www.javaboy.org");
        user.setAge(99);
        return user;
    }

    @PostMapping("/user")
    public void addUser(@RequestBody User user) {
        System.out.println(user);
    }
}
