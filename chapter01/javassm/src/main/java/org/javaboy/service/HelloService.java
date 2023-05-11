package org.javaboy.service;

import org.springframework.stereotype.Service;

/**
 * @author szh
 */
@Service
public class HelloService {

    public String sayHello() {
        return "Hello Java!";
    }
}
