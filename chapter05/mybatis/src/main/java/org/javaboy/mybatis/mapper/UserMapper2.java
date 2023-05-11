package org.javaboy.mybatis.mapper;

import org.javaboy.mybatis.model.User;

import java.util.List;

/**
 * @author szh
 */
public interface UserMapper2 {

    User getUserById(Long id);

    List<User> getAllUsers();

    Integer addUser(User user);

    Integer deleteById(Long id);

    Integer updateById(Long id, String username);

}
