package org.javaboy.test.service;

import org.javaboy.test.dao.UserDao;
import org.javaboy.test.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author szh
 */
@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public User getUserById(Long id){
         return userDao.getUserById(id);
    }

}
