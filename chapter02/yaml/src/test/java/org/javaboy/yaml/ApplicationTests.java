package org.javaboy.yaml;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ApplicationTests {

    @Autowired
    private RedisCluster redisCluster;

    @Test
    public void contextLoads() {
        System.out.println(redisCluster);
    }

}
