package org.javaboy.mybatis;

import org.javaboy.mybatis.mapper.UserMapper;
import org.javaboy.mybatis.mapper.UserMapper2;
import org.javaboy.mybatis.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        User user = userMapper.getUserById(2L);
        System.out.println(user);
    }
    @Test
    void test2(){
        List<User> users = userMapper.getAllUsers();
        System.out.println(users);
    }

    @Test
    void test3(){
        User user = new User();
        user.setUsername("lisi");
        user.setAddress("home");
        Integer i = userMapper.addUser(user);
        System.out.println(user.getId());
        System.out.println(i);
    }

    @Test
    void test4(){
        Integer i = userMapper.deleteById(4L);
        System.out.println(i);
    }

    @Test
    void test5(){
        Integer i = userMapper.updateById(2L, "javaboy3");
        System.out.println(i);
    }

    @Test
    void test6(){
        Integer i = userMapper.updateById(2L, "javaboy3");
        System.out.println(i);
    }

    @Autowired
    private UserMapper2 userMapper2;

    @Test
    void test7(){
        User user = userMapper2.getUserById(3L);
        System.out.println(user);
    }

    @Test
    void test8(){
        List<User> list = userMapper2.getAllUsers();
        System.out.println(list);
        User user = new User();
        user.setUsername("hbs");
        user.setAddress("广州");
        userMapper2.addUser(user);
        System.out.println(user.getId());
        userMapper2.deleteById(2L);
        userMapper2.updateById(5L, "lzh");
    }




}
