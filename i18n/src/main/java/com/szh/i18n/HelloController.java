package com.szh.i18n;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author szh
 * @Date 2022/9/28 9:43
 * @PackageName:com.szh.i18n
 * @ClassName: HelloController
 * @Description: TODO
 * @Version 1.0
 */
@RestController
public class HelloController {
    @Autowired
    MessageSource messageSource;

    @GetMapping("/hello")
    public String hello(){
        return messageSource.getMessage("user.name",null, LocaleContextHolder.getLocale());
    }
}
