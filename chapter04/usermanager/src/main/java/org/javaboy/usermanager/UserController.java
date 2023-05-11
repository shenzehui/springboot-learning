package org.javaboy.usermanager;

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
    public User addUser() {
        User user = new User();
        user.setUsername("lisi");
        user.setBirthday(new Date());
        return user;
    }

    /**
     * 表单传参
     */
    @PostMapping("/user1")
    public void addUser(User user) {
        System.out.println("user = " + user);
    }

    /**
     * json传参
     */
    @PostMapping("/user2")
    public void addUse2r(@RequestBody User user) {
        System.out.println("user = " + user);
    }
}
