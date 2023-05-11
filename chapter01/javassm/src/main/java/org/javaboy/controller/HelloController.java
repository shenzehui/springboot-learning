package org.javaboy.controller;

import org.javaboy.model.User;
import org.javaboy.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author szh
 */
@Controller
public class HelloController {

    @Autowired
    HelloService helloService;

    @ResponseBody
    @GetMapping("/hello")
    public String hello() {
        return helloService.sayHello();
    }

    @GetMapping("/hello2")
    public String hello2() {
        return "hello";
    }

    @ResponseBody
    @GetMapping("/data")
    public List<String> getData() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("www.justdojava.com>>>" + i);
        }
        return list;
    }

    @ResponseBody
    @GetMapping("/hello4")
    public List<User> getNames() {
        List<User> names = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            names.add(new User("javaboy:" + i, new Date()));
        }
        return names;
    }

}
