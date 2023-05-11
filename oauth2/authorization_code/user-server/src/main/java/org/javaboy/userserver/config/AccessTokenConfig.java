package org.javaboy.userserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

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

    @Bean
    TokenStore tokenStore(){
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    JwtAccessTokenConverter jwtAccessTokenConverter(){
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("javaboy");
        return converter;
    }
}
