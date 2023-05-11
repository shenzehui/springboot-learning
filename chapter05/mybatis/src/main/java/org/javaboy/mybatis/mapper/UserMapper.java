package org.javaboy.mybatis.mapper;

import org.apache.ibatis.annotations.*;
import org.javaboy.mybatis.model.User;

import java.util.List;

/**
 * @author szh
 */
//@Mapper
public interface UserMapper {
    @Select("select * from user where id = #{id}")
    User getUserById(Long id);

    @Results({@Result(property = "address", column = "uaddress"), @Result(property = "username", column = "uname"), @Result(property = "id", column = "uid")})
    @Select("select id as uid,username as uname,address as uaddress from user")
    List<User> getAllUsers();

    @Insert("insert into user (username,address) values(#{username},#{address})")
    /**
     * 主键回填
     * keyProperty:回填属性名
     * before：在操作之前回填 ？
     * resultType : 封装数据对象
     */
    @SelectKey(statement = "select last_insert_id()", keyProperty = "id", before = false, resultType = Long.class)
    Integer addUser(User user);

    @Delete("delete from user where id = #{id}")
    Integer deleteById(Long id);

    @Update("update user set username = #{username} where id = #{id}")
    Integer updateById(Long id, String username);

}
