package org.javaboy.idempontent.contrller;


import org.javaboy.idempontent.anno.AutoIdempotent;
import org.javaboy.idempontent.token.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author szh
 */
@RestController
public class HelloController {

    @Autowired
    TokenService tokenService;

    @GetMapping("/gettoken")
    public String getToken(){
        return tokenService.createToken();
    }

    @PostMapping("/hello")
    @AutoIdempotent
    public String hello(){
        return "hello";
    }

    @PostMapping("/hello2")
    public String hello2(){
        return "hello2";
    }


}
