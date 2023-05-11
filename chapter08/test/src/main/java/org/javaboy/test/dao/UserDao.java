package org.javaboy.test.dao;

import org.javaboy.test.model.User;
import org.springframework.stereotype.Repository;

/**
 * @author szh
 */
@Repository
public class UserDao {
    public User getUserById(Long id){
        User user = new User();
        user.setId(id);
        user.setUsername("111");
        return user;
    }
}
