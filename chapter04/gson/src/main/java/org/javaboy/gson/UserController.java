package org.javaboy.gson;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author szh
 */
@RestController
public class UserController {

    @GetMapping("/user")
    public User getUser() {
        User user = new User();
        user.setUserName("javaboy");
        user.setBirthday(new Date());
        return user;
    }

}
