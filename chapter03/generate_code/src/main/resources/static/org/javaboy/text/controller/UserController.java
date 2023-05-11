package org.javaboy.text.controller;

import org.javaboy.text.model.User;
import org.javaboy.text.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@RestController
public class UserController{

    @Autowired
    UserService userService;

    @GetMapping("/users")
    public List<User> getAllUser(){
        return userService.getAllUsers();
    }
}