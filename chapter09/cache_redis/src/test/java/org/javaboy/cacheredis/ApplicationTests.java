package org.javaboy.cacheredis;

import org.javaboy.cacheredis.model.User;
import org.javaboy.cacheredis.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {

    @Autowired
    UserService userService;

    @Test
    void contextLoads() {
        for (int i = 0; i < 3; i++) {
            User u = userService.getUserById(98L);
            System.out.println("u = " + u);
        }
    }
    @Test
    void test2() {
        User u1 = userService.getUserById2(98L,"zhangsan");
        User u2 = userService.getUserById2(98L,"lisi");
        System.out.println("u1 = " + u1);
        System.out.println("u2 = " + u2);
    }

    @Test
    void test3(){
        User u1 = userService.getUserById3(98L,"zhangsan");
        User u2 = userService.getUserById3(98L,"lisi");
        System.out.println("u1 = " + u1);
        System.out.println("u2 = " + u2);
    }

    /**
     * getUserById:100
     * u2 = User{id=100, username='wangwu'}
     * 第二次直接从缓存中获取数据
     */
    @Test
    void test4(){
        User user = userService.getUserById(100L);
        user.setUsername("wangwu");
        userService.updateUserById(user);
        User u2 = userService.getUserById(100L);
        System.out.println("u2 = " + u2);
    }

    @Test
    void test5(){
        /*添加缓存*/
        userService.getUserById(100L);
        /*删除缓存*/
        userService.deleteUserById(100L);
        /*查询缓存没有记录，又添加缓存*/
        User user = userService.getUserById(100L);
        System.out.println("user = " + user);
    }


}
