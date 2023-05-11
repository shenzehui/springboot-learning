package org.javaboy.idempontent.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author szh
 */
@Service
public class RedisService {

    @Autowired
    StringRedisTemplate redisTemplate;

    public boolean setEx(String key, String value, Long expireTime) {
        boolean result = false;
        try {
            ValueOperations<String, String> ops = redisTemplate.opsForValue();
            ops.set(key, value);
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean exists(String key) {
        return redisTemplate.hasKey(key);
    }

    public boolean remove(String key) {
        if (exists(key)) {
            return redisTemplate.delete(key);
        }
        return false;
    }

}
