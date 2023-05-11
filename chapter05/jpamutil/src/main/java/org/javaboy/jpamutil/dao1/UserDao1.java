package org.javaboy.jpamutil.dao1;

import org.javaboy.jpamutil.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author szh
 */
public interface UserDao1 extends JpaRepository<User,Long> {

}
