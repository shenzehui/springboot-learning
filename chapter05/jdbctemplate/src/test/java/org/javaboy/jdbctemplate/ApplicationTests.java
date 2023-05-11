package org.javaboy.jdbctemplate;

import org.javaboy.jdbctemplate.mode.User;
import org.javaboy.jdbctemplate.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@SpringBootTest
public class ApplicationTests {
    @Autowired
    private UserService userService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void contextLoads() {
        User user = new User();
        user.setUsername("javaboy");
        user.setAddress("org.javaboy.cn");
        int i = userService.addUser(user);
        System.out.println(i);
    }

    @Test
    public void test(){
        User user = new User();
        user.setUsername("itboyhub");
        user.setAddress("www.itboy.com");
        int i = userService.addUser2(user);
        System.out.println("影响行数= "+ i + ",id = "+user.getId());
    }

    @Test
    public void test2(){
        int i = userService.deleteById(1L);
        System.out.println(i);
    }

    @Test
    public void test3(){
        int i = userService.updateById(2L,"javaboy2");
        System.out.println(i);
    }

    @Test
    public void test4(){
        List<User> list = userService.getAllUsers();
        for (User user : list) {
            System.out.println(user);
        }
    }

    @Test
    public void test5(){
        List<User> list = userService.getAllUsers2();
        for (User user : list) {
            System.out.println(user);
        }
    }

}
