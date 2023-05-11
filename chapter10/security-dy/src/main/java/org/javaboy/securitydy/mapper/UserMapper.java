package org.javaboy.securitydy.mapper;

import org.javaboy.securitydy.bean.Role;
import org.javaboy.securitydy.bean.User;

import java.util.List;

/**
 * @author szh
 */
public interface UserMapper {
    /**
     * 根据用户名查询用户
     *
     * @param username
     * @return
     */
    User loadUserByUsername(String username);

    /**
     * 根据用户 id 查询角色
     *
     * @param id
     * @return
     */
    List<Role> getRolesById(Integer id);
}
