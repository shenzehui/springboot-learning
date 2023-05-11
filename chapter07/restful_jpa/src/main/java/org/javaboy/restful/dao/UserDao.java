package org.javaboy.restful.dao;

import org.javaboy.restful.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

/**
 * path默认是：users是默认类名小写，并加上s
 *
 * @author szh
 */
@RepositoryRestResource(path = "people", collectionResourceRel = "us", itemResourceRel = "u") // 指定路径和key和link下的key
public interface UserDao extends JpaRepository<User, Long> {
    /**
     * @Param标记一下，规定前端传参名称
     * http://localhost:8080/users/search查询所有自定义接口
     * http://localhost:8080/users/search/findUserByUsernameEquals?username=王五
     *
     * */
    /**
     * @param username
     * @return
     * @RestResource: 作用是修改接口名，防止接口名过长导致臃肿
     * exported:是否暴露接口 默认是true
     * path：请求路径（默认是方法名）
     * rel: http://localhost:8080/users/search 查询到的接口名(默认是方法名)
     */
    @RestResource(exported = true, path = "byname", rel = "byname")
    List<User> findUserByUsernameEquals(@Param("username") String username);

    /**
     * 作用2
     * 屏蔽jpa默认提供的方法
     *
     * @param aLong
     */
    @Override
    @RestResource(exported = false)
    void deleteById(Long aLong);
}
