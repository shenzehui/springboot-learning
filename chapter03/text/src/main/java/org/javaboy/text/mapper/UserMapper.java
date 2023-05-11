package org.javaboy.text.mapper;

import org.javaboy.text.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author szh
 */
@Mapper
public interface UserMapper {
    /**
     * 获取所有用户
     *
     * @return
     */
    List<User> getAllUsers();
}