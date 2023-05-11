package org.javaboy.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

/**
 * @author szh
 */
@RestController
public class HelloController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    private void Hello() {
        for (int i = 0; i < 100000; i++) {
            LOGGER.info("hello java");
        }
    }

}
