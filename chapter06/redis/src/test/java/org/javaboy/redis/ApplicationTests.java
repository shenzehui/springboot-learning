package org.javaboy.redis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.javaboy.redis.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@SpringBootTest
class ApplicationTests {

    /**
     * 会自动对key和value进行序列化 String类型也会序列化
     */
    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Test
    void contextLoads() {
        User user = new User();
        user.setUsername("javaboy");
        user.setAddress("www.javaboy.org");
        ValueOperations ops = redisTemplate.opsForValue();
        // 将对象自动序列化成JSON存入Redis中   前提：对象实现Serializable解耦
        ops.set("u", user);
        User u = (User) ops.get("u");
        System.out.println("user = " + u);
    }

    @Test
    void test2() {
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        ops.set("javaboy", "wwww.javaboy.org");
        String s = ops.get("javaboy");
        System.out.println("s = " + s);
    }

    /**
     * 用stringRedisTemplate存对象，先把对象序列化成json
     */
    @Test
    void test3() throws JsonProcessingException {
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        User user = new User();
        user.setUsername("javaboy");
        user.setAddress("www.javaboy.org");

        // 转为json字符串
        ObjectMapper om = new ObjectMapper();
        String u1 = om.writeValueAsString(user);
        ops.set("u1", u1);
        String user1 = ops.get("u1");
        System.out.println("user1 = " + user1);
    }

}
