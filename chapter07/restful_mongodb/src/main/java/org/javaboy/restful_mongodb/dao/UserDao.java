package org.javaboy.restful_mongodb.dao;

import org.javaboy.restful_mongodb.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author szh
 */
public interface UserDao extends MongoRepository<User, Long> {
}
