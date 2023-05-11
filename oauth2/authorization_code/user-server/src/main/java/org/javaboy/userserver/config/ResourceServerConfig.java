package org.javaboy.userserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * @Author szh
 * @Date 2022/7/21 13:29
 * @PackageName:org.javaboy.userserver.config
 * @ClassName: ResourceServerConfig
 * @Description: TODO
 * @Version 1.0
 */
@Configuration
@EnableResourceServer  //开启资源服务器
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    TokenStore tokenStore;

//    @Bean
//    RemoteTokenServices tokenServices(){
//        RemoteTokenServices services = new RemoteTokenServices();
//        services.setCheckTokenEndpointUrl("http://localhost:8080/oauth/check_token"); //检查用户传递过来的token正确性的访问地址
//        services.setClientId("javaboy");
//        services.setClientSecret("123");
//        return services;
//    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
//        resources.resourceId("res1").tokenServices(tokenServices());
        // 校验用户传递过来的jwt的正确性
        resources.resourceId("res1").tokenStore(tokenStore);
    }

    @Override //配置拦截规则
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin/**").hasRole("admin")
                .anyRequest().authenticated().and().cors();
    }
}