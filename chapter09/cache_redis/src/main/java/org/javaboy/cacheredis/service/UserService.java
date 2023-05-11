package org.javaboy.cacheredis.service;

import org.javaboy.cacheredis.model.User;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author szh
 */
@Service
@CacheConfig() // 做一些全局性配置 cacheManager和cacheResolver一般不需要配置
public class UserService {

    /*
     * @Cacheable
     *        cacheName：redis中key的前缀名
     * 标记在方法上，表示该方法的返回结果需要缓存，默认情况下，方法的参数将作为缓存的key
     * 执行流程：  寻找传来的参数在redis中是否有相对应的key
     *           若无，则执行service以下方法，并将数据存入redis中， key默认是参数（多个也可以），value是方法返回的值
     *           若有，则跳过以下方法，去redis寻找对应key的value值，最后返回
     */
    @Cacheable(cacheNames = "c1")
    public User getUserById(Long id) {
        System.out.println("getUserById:" + id);
        User user = new User();
        user.setId(id);
        user.setUsername("javaboy");
        return user;
    }

    /**
     * 更新操作 根据id来进行更新
     * 如果缓存不存在，则进行缓存，否则进行更新
     *
     * @param user
     * @return
     */
    @CachePut(cacheNames = "c1", key = "#user.id")
    public User updateUserById(User user) {
        return user;
    }

    /**
     * 删除缓存数据
     * beforeInvocation = false 先删数据库还是先删缓存 默认是false 先删数据库
     */
    @CacheEvict(cacheNames = "c1", beforeInvocation = false)
    public void deleteUserById(Long id) {
        System.out.println("deleteUserById");

    }

    /**
     * 如果方法存在多个参数，则默认情况下，多个参数共同作为缓存的 key,
     * <p>
     * 也可以自己指定缓存 的key  通过 '#+参数名'  #id:只用id作为参数名  但逻辑上有错误
     * <p>
     * 也可以使用 SPEL 表达式  #root.method.name 用方法名作为缓存的key  没有参数的时候可以使用
     *
     * @param id
     * @param username
     * @return
     */
    @Cacheable(cacheNames = "c2", key = "#root.method.name")
    public User getUserById2(Long id, String username) {
        System.out.println("getUserById2:" + id);
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        return user;
    }

    /**
     * 自定义Key
     */
    @Cacheable(cacheNames = "c3", keyGenerator = "myKeyGenerator")
    public User getUserById3(Long id, String username) {
        System.out.println("getUserById3:" + id);
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        return user;
    }
}