package org.javaboy.authserver.config;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author szh
 * @Date 2022/7/22 13:50
 * @PackageName:org.javaboy.authserver.config
 * @ClassName: MyAdditionalInformation
 * @Description: 自定义请求返回信息
 * @Version 1.0
 */
@Component
public class MyAdditionalInformation implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        Map<String, Object> map = oAuth2AccessToken.getAdditionalInformation();
        map.put("site","www.javaboy.org");
        map.put("email","3032388097@qq.com");
        ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(map);
        return oAuth2AccessToken;
    }
}
