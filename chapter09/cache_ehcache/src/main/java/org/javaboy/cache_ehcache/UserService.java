package org.javaboy.cache_ehcache;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author szh
 */
@Service
public class UserService {
    /**
     * name 属性这里是指定缓存策略的名字 这个也可以通过 @CacheConifg 指定在类上面
     */
    @Cacheable(cacheNames = "user")
    public User getUserById(Long id) {
        System.out.println("id = " + id);
        User user = new User();
        user.setId(id);
        user.setUsername("javaboy");
        return user;
    }
}
