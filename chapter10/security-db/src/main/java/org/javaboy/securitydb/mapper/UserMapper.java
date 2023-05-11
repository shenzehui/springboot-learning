package org.javaboy.securitydb.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.javaboy.securitydb.bean.Role;
import org.javaboy.securitydb.bean.User;

import java.util.List;

@Mapper
public interface UserMapper {
    /**
     * 根据用户名查询用户
     *
     * @param username
     * @return
     */
    User loadUserByUsername(String username);

    /**
     * 根据 uid 关联查询用户角色
     *
     * @param id
     * @return
     */
    List<Role> getUserRoleById(Integer id);
}
