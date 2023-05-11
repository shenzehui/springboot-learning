package org.javaboy.xml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author szh
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    public void hello(){
        System.out.println(userService.hello());
    }
}
