package com.szh.authorizationserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

/**
 * 自定义授权服务器配置
 * Created by szh on 2023-05-11
 *
 * @author szh
 */

@Configuration
@EnableAuthorizationServer // 指定当前应用为授权服务器
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * 用来配置授权服务器可以为哪些服务器授权
     * id、secret、redirectURI、使用那种授权模式
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 基于内存
        clients.inMemory()
                // 客户端 id
                .withClient("client")
                // 秘钥
                .secret(passwordEncoder.encode("123"))
                // 重定向地址
                .redirectUris("http://www.baidu.com")
                // 授权服务器支持的模式，这里只支持授权码模式
                .authorizedGrantTypes("authorization_code")
                // 令牌允许获取的资源权限
                .scopes("read:user");
    }

    /**
     * 配置授权服务器使用哪个 userDetailsService
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.userDetailsService(userDetailsService);
    }

    /**
     * 授权码这种模式：
     * 1.请求用户是否授权；/oauth/authorize
     *   完整路径：
     *   http://localhost:8080/oauth/authorize?client_id=client&response_type=code&redirect_uri=http://www.baidu.com
     * 2.授权之后根据获取的授权码获取令牌 /oauth/token
     *  完整路径：
     *  curl -X POST -H "Content-Type: application/x-www-form-urlencoded" -d 'grant_type=authorization_code&code=IwvCtx&redirect_uri=http://www.baidu.com' "http://client:secret@localhost:8080/oauth/token
     * 3. 支持令牌刷新 /oauth/token id secret 授权类型： refresh_token 刷新的令牌：refresh_token
     *  完整路径:
     *  curl -X POST -H "Content-Type: application/x-www-form-urlencoded" -d 'grant_type=refresh_token&refresh_token=f6583d8a-598c-46bb-81d8-01fa6484cf05&client_id=client' "http://client:secret@localhost:8080/oauth/token"
     *
     */
}
