package org.javaboy.formlogin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author szh
 * @Date 2022/5/19 15:36
 * @PackageName:org.javaboy.formlogin
 * @ClassName: HelloController
 * @Description: TODO
 * @Version 1.0
 */
@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello(){
        return "hello security!";
    }

    @GetMapping("/success")
    public String success(){
        return "success";
    }

    @GetMapping("/admin/hello")
    public String admin(){
        return "hello admin";
    }

    @GetMapping("/user/hello")
    public String user(){
        return "hello user";
    }
}
