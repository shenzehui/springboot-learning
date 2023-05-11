package org.javaboy.freemarker.controller;

import org.javaboy.freemarker.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;

/**
 * @author szh
 */
@Controller
public class UserController {

    @GetMapping("/user")
    public String user(Model model) {
        List<User> users = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setId((long) i);
            user.setUsername("javaboy>>>" + i);
            user.setAddress("www.javaboy.org>>>" + i);
            // 0表示男 1表示女 其他数字未知
            user.setGender(random.nextInt(3));
            users.add(user);
        }
        model.addAttribute("users", users);
        model.addAttribute("hello", "<h1>hello</h1>");
        model.addAttribute("world", "<h1>world</h1>");
        Map<String, Object> info = new HashMap<>();
        info.put("name", "江南一点雨");
        info.put("age", 99);
        model.addAttribute("info", info);
        model.addAttribute("name", "javaboy");
        model.addAttribute("birthday", new Date());
        return "user";
    }

}
