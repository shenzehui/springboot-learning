package org.javaboy.text.mapper;

import org.javaboy.text.model.User;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface UserMapper{
    List<User> getAllUsers();
}