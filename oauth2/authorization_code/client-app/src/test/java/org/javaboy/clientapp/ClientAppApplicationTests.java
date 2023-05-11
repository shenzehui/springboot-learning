package org.javaboy.clientapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@SpringBootTest
class ClientAppApplicationTests {

    @Autowired
    RestTemplate restTemplate;

    @Test
    void contextLoads() {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("client_id", "javaboy");
        map.add("client_secret", "123");
        map.add("grant_type", "client_credentials"); //授权模式
        Map resp = restTemplate.postForObject("http://localhost:8080/oauth/token", map, Map.class);
        System.out.println("resp = " + resp);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + resp.get("access_token"));
        HttpEntity<?> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<String> respEntity = restTemplate.exchange("http://localhost:8081/hello", HttpMethod.GET, httpEntity, String.class);
        System.out.println("respEntity.getBody() = " + respEntity.getBody());
    }

}
