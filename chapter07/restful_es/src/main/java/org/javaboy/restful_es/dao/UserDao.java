package org.javaboy.restful_es.dao;

import org.javaboy.restful_es.model.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * ElasticsearchRepository
 *
 * @author szh
 */
public interface UserDao extends ElasticsearchRepository<User, Long> {

}
