package org.javaboy.clientapp.component;

import org.javaboy.clientapp.controller.HelloController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @Author szh
 * @Date 2022/7/22 13:04
 * @PackageName:org.javaboy.clientapp.component
 * @ClassName: TokenTask
 * @Description: TODO
 * @Version 1.0
 */
@Component
public class TokenTask {

    @Autowired
    RestTemplate restTemplate;

    public String access_token = "";

    public String refresh_token = "";

    /**
     * 通过授权码来请求授权服务器获取token，并从资源服务器加载数据
     */
    public String getData(String code) {
        if ("".equals(access_token) && code != null) {
            MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
            map.add("code", code);
            map.add("client_id", "javaboy");
            map.add("client_secret", "123");
            map.add("redirect_uri", "http://localhost:8082/index.html");
            map.add("grant_type", "authorization_code");
            Map<String, String> resp = restTemplate.postForObject("http://localhost:8080/oauth/token", map, Map.class);
            access_token = resp.get("access_token");
            refresh_token = resp.get("refresh_token");
            return loadDataFromResServer();
        } else {
            return loadDataFromResServer();
        }
    }

    /**
     * 请求资源服务器加载数据
     */
    private String loadDataFromResServer() {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Bearer " + access_token);
            HttpEntity httpEntity = new HttpEntity(headers);
            ResponseEntity<String> respEntity = restTemplate.exchange("http://localhost:8081/admin/hello", HttpMethod.GET, httpEntity, String.class);
            return respEntity.getBody();
        } catch (RestClientException e) {
            return "未加载";
        }
    }

    /**
     * 每定时刷新token
     */
    @Scheduled(cron = "0 55 0/1 * * ?")
    public void tokenTask() {
        System.out.println("执行定时任务");
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("client_id", "javaboy");
        map.add("client_secret", "123");
        map.add("refresh_token", refresh_token);
        map.add("grant_type", "refresh_token");
        Map<String, String> resp = restTemplate.postForObject("http://localhost:8080/oauth/token", map, Map.class);
        access_token = resp.get("access_token");
        refresh_token = resp.get("refresh_token");
    }

}
