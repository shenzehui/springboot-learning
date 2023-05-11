package org.javaboy.authserver.config;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.xml.ws.handler.Handler;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author szh
 * @Date 2022/7/22 19:24
 * @PackageName:org.javaboy.authserver.config
 * @ClassName: MyJwtConverter
 * @Description: TODO
 * @Version 1.0
 */
public class MyJwtConverter extends JwtAccessTokenConverter {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Map<String, Object> additionalInformation = new LinkedHashMap<>();
        Map<String, Object> info = new LinkedHashMap<>();
        additionalInformation.put("author", "江南一点雨");
        additionalInformation.put("微信公众号", "江南一点雨");
        additionalInformation.put("user", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        info.put("info", additionalInformation);
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
        return super.enhance(accessToken, authentication);
    }
}
