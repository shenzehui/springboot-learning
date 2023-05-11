package org.javaboy.securitydb.service;

import org.javaboy.securitydb.bean.User;
import org.javaboy.securitydb.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 实现 UserDetailsService 接口
 *
 * @author szh
 */
@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserMapper userMapper;

    /**
     * 根据用户名查询用户  username ：用户名
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.loadUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在！");
        }
        // 根据用户 id 查询用户角色
        user.setRoles(userMapper.getUserRoleById(user.getId()));
        return user;
        // 返回user后，security会自动根据密码判断是否正确！！!
    }

}
