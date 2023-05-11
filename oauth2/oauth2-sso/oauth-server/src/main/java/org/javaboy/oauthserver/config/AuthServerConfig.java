package org.javaboy.oauthserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * @Author szh
 * @Date 2022/7/22 14:28
 * @PackageName:org.javaboy.oauthserver.config
 * @ClassName: AuthServerConfig
 * @Description: TODO
 * @Version 1.0
 */
@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory().withClient("javaboy")
                .secret(passwordEncoder.encode("123"))
                .autoApprove(true)
                .redirectUris("http://localhost:1112/login","http://localhost:1113/login")
                .scopes("all")
                .authorizedGrantTypes("authorization_code")
                .accessTokenValiditySeconds(7200);
    }
}
