package org.javaboy.thymeleaf;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ApplicationTests {
    /**
     * 手动渲染模板
     */
    @Autowired
    TemplateEngine templateEngine;

    @Test
    public void contextLoads() {
        Context ctx = new Context();
        ctx.setVariable("username", "javaboy");
        ctx.setVariable("position", "java 工程师");
        ctx.setVariable("salary", "30000");
        /**
         * template:模板名称
         * ctx参数封装
         */
        String mail = templateEngine.process("mail", ctx);
        System.out.println(mail);
    }
}
