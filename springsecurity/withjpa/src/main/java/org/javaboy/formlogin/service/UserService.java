package org.javaboy.formlogin.service;

import org.javaboy.formlogin.dao.UserDao;
import org.javaboy.formlogin.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @Author szh
 * @Date 2022/5/19 19:39
 * @PackageName:org.javaboy.formlogin.service
 * @ClassName: UserService
 * @Description: TODO
 * @Version 1.0
 */
@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findUserByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("用户不存在");
        }
        return user;
    }
}
