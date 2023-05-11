package org.javaboy.formlogin.dao;

import org.javaboy.formlogin.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author szh
 * @Date 2022/5/19 19:38
 * @PackageName:org.javaboy.formlogin.dao
 * @ClassName: UserDao
 * @Description: TODO
 * @Version 1.0
 */
public interface UserDao extends JpaRepository<User,Long> {
    User findUserByUsername(String username);
}
