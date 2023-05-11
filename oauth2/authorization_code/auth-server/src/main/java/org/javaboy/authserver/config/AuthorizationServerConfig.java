package org.javaboy.authserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.sql.DataSource;
import java.util.Arrays;

/**
 * @Author szh
 * @Date 2022/7/21 13:12
 * @PackageName:org.javaboy.authserver.config
 * @ClassName: AuthorizationServerConfig
 * @Description: 授权服务器配置
 * @Version 1.0
 */
@Configuration
@EnableAuthorizationServer //开启授权服务
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    TokenStore tokenStore;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    DataSource dataSource;

    @Autowired
    JwtAccessTokenConverter jwtAccessTokenConverter;

    @Autowired
    MyAdditionalInformation myAdditionalInformation;

    @Bean
    ClientDetailsService clientDetailsService() {
        return new JdbcClientDetailsService(dataSource);
    }

    @Bean
    AuthorizationServerTokenServices tokenServices() {
        // 定义token生成类
        DefaultTokenServices services = new DefaultTokenServices();
        services.setClientDetailsService(clientDetailsService());
        // 是否支持刷新
        services.setSupportRefreshToken(true);
        // 生成的token要保存的位置，这里我们配置的是保存在内存中
        services.setTokenStore(tokenStore);
//        services.setAccessTokenValiditySeconds(60 * 60 * 2); //token有效期
//        services.setRefreshTokenValiditySeconds(60 * 60 * 24 * 7); //刷新token 7天

        //token处理链，将token通过jwtAccessTokenConverter转化为jwt
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        // jwtAccessTokenConverter 返回jwt   myAdditionalInformation 返回自定义信息内容
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(jwtAccessTokenConverter, myAdditionalInformation));
        services.setTokenEnhancer(tokenEnhancerChain);
        return services;
    }

    /**
     * 配置一些安全规则
     *
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        //  /oauth/check_token  资源服务器可以调用这个接口，检查token的有效性，用户信息
        security.checkTokenAccess("permitAll()")
                // 允许表单登录
                .allowFormAuthenticationForClients();
    }

    /**
     * 配置客户端信息
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.inMemory()   //内存中保存客户端信息
//                .withClient("javaboy")
//                .secret(passwordEncoder.encode("123"))  //客户端用户信息
//                .resourceIds("res1")
//                .authorizedGrantTypes("authorization_code", "refresh_token", "implicit", "password","client_credentials") //授权模式：  authorization_code 授权码模式  implicit 简化模式
//                .scopes("all") //客户端授权范围                                                  //  password  密码模式      client_credentials 客户端模式
//                .autoApprove(true)  //自动授权
//                .redirectUris("http://localhost:8082/01.html", "http://localhost:8082/02.html");  //重定向地址

        // 客户端信息存放在数据库中
        clients.withClientDetails(clientDetailsService());
    }

    /**
     * 配置端点信息
     *
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.
                // 密码模式所需配置
                        authenticationManager(authenticationManager).
                // 授权码存放位置（内存）
                        authorizationCodeServices(authorizationCodeServices()).tokenServices(tokenServices());
    }

    @Bean
    AuthorizationCodeServices authorizationCodeServices() {  //授权码存放位置
        return new InMemoryAuthorizationCodeServices();
    }
}
