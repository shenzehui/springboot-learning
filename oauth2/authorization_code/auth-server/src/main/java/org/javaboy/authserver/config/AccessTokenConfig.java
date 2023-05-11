package org.javaboy.authserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * @Author szh
 * @Date 2022/7/21 13:12
 * @PackageName:org.javaboy.authserver.config
 * @ClassName: AccessTokenConfig
 * @Description: TODO
 * @Version 1.0
 */
@Configuration
public class AccessTokenConfig {

//    @Autowired
//    RedisConnectionFactory redisConnectionFactory;

    @Bean
    TokenStore tokenStore() {
//        return new InMemoryTokenStore();  // token 保存在内存中
//        return new RedisTokenStore(redisConnectionFactory);  // token 保存在 redis 中

        // 使用 jwt 来请求资源服务器
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    JwtAccessTokenConverter jwtAccessTokenConverter() {
        // 这里将自定义信息添加到 token 中
        JwtAccessTokenConverter converter = new MyJwtConverter();
        converter.setSigningKey("javaboy");
        return converter;
    }
}
