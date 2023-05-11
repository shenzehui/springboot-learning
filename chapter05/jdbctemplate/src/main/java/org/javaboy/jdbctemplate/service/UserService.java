package org.javaboy.jdbctemplate.service;

import org.javaboy.jdbctemplate.mode.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.List;

/**
 * @author szh
 */
@Service
public class UserService {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public int addUser(User user) {
        int result = jdbcTemplate.update("insert into user(username,address) values(?,?)", user.getUsername(), user.getAddress());
        return result;
    }

    public int addUser2(User user) {
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        int result = jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement("insert into user(username,address) values(?,?)", Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, user.getUsername());
                ps.setString(2, user.getUsername());
                return ps;
            }
            // 将 key 放入到 GeneratedKeyHolder 中
        }, keyHolder);
        // 从 keyHolder 获取 key 放入到 user 对象中
        user.setId(keyHolder.getKey().longValue());
        return result;
    }

    public int deleteById(Long id) {
        return jdbcTemplate.update("delete from user where id = ?", id);
    }

    public int updateById(Long id, String username) {
        return jdbcTemplate.update("update user set username = ? where id = ?", username, id);
    }

    public List<User> getAllUsers() {
        // 数据库字段名和Java类属性名不一致的时候
        List<User> users = jdbcTemplate.query("select * from user", new RowMapper<User>() {
            // 说明映射关系
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                String username = rs.getString("username");
                String address = rs.getString("address");
                long id = rs.getLong("id");
                User user = new User();
                user.setId(id);
                user.setUsername(username);
                user.setAddress(address);
                return user;
            }
        });
        return users;
    }

    public List<User> getAllUsers2() {
        // 数据库字段名和 Java 类属性名一致的时候
        List<User> users = jdbcTemplate.query("select * from user", new BeanPropertyRowMapper<>(User.class));
        return users;
    }

}
