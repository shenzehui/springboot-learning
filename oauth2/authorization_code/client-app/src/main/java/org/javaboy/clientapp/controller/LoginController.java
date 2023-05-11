package org.javaboy.clientapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @Author szh
 * @Date 2022/7/22 10:31
 * @PackageName:org.javaboy.clientapp.controller
 * @ClassName: LoginController
 * @Description: TODO
 * @Version 1.0
 */
@Controller
public class LoginController {

    @Autowired
    RestTemplate restTemplate;

    @PostMapping("/login")
    public String login(String username, String password, Model model) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("username", username);
        map.add("password", password);
        map.add("client_id", "javaboy");
        map.add("client_secret", "123");
        // 授权模式
        map.add("grant_type", "password");
        Map resp = restTemplate.postForObject("http://localhost:8080/oauth/token", map, Map.class);
        System.out.println("resp = " + resp);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + resp.get("access_token"));
        HttpEntity<?> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<String> respEntity = restTemplate.exchange("http://localhost:8081/hello", HttpMethod.GET, httpEntity, String.class);
        model.addAttribute("msg", respEntity.getBody());
        return "02.html";
    }

    @RequestMapping("/02.html")
    public String loginPage() {
        return "02";
    }
}
