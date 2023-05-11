package org.javaboy.aop.service;

import org.springframework.stereotype.Service;

/**
 * @author szh
 */
@Service
public class UserService {
    public String getUserById(Integer id) {
        System.out.println("getUserById");
        int i = 1 / 0;
        return "user";
    }

    public void deleteUserById(Integer id) {
        System.out.println("delete id:" + id);
    }

}
