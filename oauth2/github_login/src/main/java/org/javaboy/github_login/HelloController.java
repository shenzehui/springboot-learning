package org.javaboy.github_login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author szh
 * @Date 2022/7/22 18:30
 * @PackageName:org.javaboy.github_login
 * @ClassName: HelloController
 * @Description: TODO
 * @Version 1.0
 */
@Controller
public class HelloController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/index.html")
    public String index() {
        return "index";
    }

    @GetMapping("/authorization_code")
    public String authorization_code(String code) {
        Map<String, String> map = new HashMap();
        map.put("client_id", "9b2e4e6fbe5e0d8c9059");
        map.put("client_secret", "f5d5b6dcae9a4b054c6e3f45443b48fab4389603");
        map.put("code", code);
        map.put("state", "szh");
        map.put("redirect_uri", "http://localhost:8080/authorization_code");

        // 请求资源服务器获取 token
        Map<String, String> resp = restTemplate.postForObject("https://github.com/login/oauth/access_token", map, Map.class);

        // 将 token 设置到请求头中
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "token " + resp.get("access_token"));
        HttpEntity<Map> httpEntity = new HttpEntity(headers);

        // 请求获取当前用户
        ResponseEntity<Map> response = restTemplate.exchange("https://api.github.com/user", HttpMethod.GET, httpEntity, Map.class);

        // 输出响应用户信息
        System.out.println("response.getBody() = " + response.getBody());
        return "forward:/index.html";
    }
}
