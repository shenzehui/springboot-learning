package org.javaboy.jpamutil.dao2;

import org.javaboy.jpamutil.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author szh
 */
public interface UserDao2 extends JpaRepository<User, Long> {

}
