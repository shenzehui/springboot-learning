package org.javaboy.clientapp.controller;

import org.javaboy.clientapp.component.TokenTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @Author szh
 * @Date 2022/7/21 13:47
 * @PackageName:org.javaboy.clientapp.controller
 * @ClassName: HelloController
 * @Description: TODO
 * @Version 1.0
 */
@Controller
public class HelloController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private TokenTask tokenTask;

    @GetMapping("/index.html")
    public String index(String code, Model model) {
//        if (code != null) {
//            //封装k-v参数
//            MultiValueMap<String, String> map = new LinkedMultiValueMap();
//            map.add("code", code);  //授权码
//            map.add("client_id", "javaboy"); //客户端id
//            map.add("client_secret", "123"); //客户端密码
//            map.add("redirect_uri", "http://localhost:8082/index.html"); //重定向地址
//            map.add("grant_type", "authorization_code");  //授权类型
//            Map<String, String> resp = restTemplate.postForObject("http://localhost:8080/oauth/token", map, Map.class);//用授权码请求授权服务器返回token
//            System.out.println("resp = " + resp);  //其中有token信息
//            HttpHeaders headers = new HttpHeaders();
//            headers.add("Authorization", "Bearer " + resp.get("access_token"));
//            HttpEntity<?> httpEntity = new HttpEntity<>(headers);
//            ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8081/hello", HttpMethod.GET, httpEntity, String.class);//去资源访问器访问资源(hello接口)
//            model.addAttribute("msg",responseEntity.getBody());  //传递资源服务器返回结果中的请求体到前端
//        }
        String data = tokenTask.getData(code);
        model.addAttribute("msg", data);
        return "index";
    }
}
