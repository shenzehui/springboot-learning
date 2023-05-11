package org.javaboy.sessionshare;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author szh
 */
@RestController
public class HelloController {

    @Value("${server.port}")
    Integer port;

    @GetMapping("/set")
    public String set(HttpSession httpSession) {
        httpSession.setAttribute("javaboy", "www.javaboy.org");
        return String.valueOf(port);
    }

    @GetMapping("/get")
    public String get(HttpSession session) {
        String javaboy = (String) session.getAttribute("javaboy");
        return javaboy + ":" + port;
    }

}
