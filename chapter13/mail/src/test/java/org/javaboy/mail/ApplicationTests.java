package org.javaboy.mail;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.javaboy.mail.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Date;

@SpringBootTest
class ApplicationTests {

    @Autowired
    /*实现类JavaMailSenderImpl*/
    JavaMailSender javaMailSender;

    @Test
    void contextLoads() {
        SimpleMailMessage simpMsg = new SimpleMailMessage();
        /*发送者*/
        simpMsg.setFrom("3032388097@qq.com");
        /*收件人*/
        simpMsg.setTo("2458528193@qq.com");
        simpMsg.setSentDate(new Date());
        /*邮件主题*/
        simpMsg.setSubject("邮件主题-测试邮件");
        simpMsg.setText("邮件内容-测试邮件");
        javaMailSender.send(simpMsg);
    }

    /*发送带附件邮件*/
    @Test
    void test1() throws MessagingException {
        File file = new File("C:\\Users\\ASUS\\Pictures\\Saved Pictures\\aijiang.jpeg");
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom("3032388097@qq.com");
        helper.setTo("2458528193@qq.com");
        helper.setSentDate(new Date());
        helper.setSubject("邮件主题-测试邮件");
        helper.setText("邮件内容-测试邮件");
        /*添加附件*/
        helper.addAttachment(file.getName(), file);
        javaMailSender.send(mimeMessage);
    }

    /*发送带图片资源的邮件 是正文中有图片 不是附件（不建议放在内容中）*/
    @Test
    void test2() throws MessagingException {
        File file = new File("C:\\Users\\ASUS\\Pictures\\Saved Pictures\\aijiang.jpeg");
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom("3032388097@qq.com");
        helper.setTo("2582883581@qq.com");
        helper.setSentDate(new Date());
        helper.setSubject("邮件主题-测试邮件");
        /*html 参数  设置为true 邮件内容支持html格式  通过html传递邮件内容，图片放在html中*/
        helper.setText("<div>hello，这是一封带图片资源的文件。。。</div><p>哀酱可爱捏！！！</p><div><img src='cid:p01'/></div>",true);
        /*配置p01图片  p01自定义*/
        helper.addInline("p01", file);
        javaMailSender.send(mimeMessage);
    }

    @Test
    void test3() throws MessagingException, IOException, TemplateException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom("2458528193@qq.com");
        helper.setTo("3032388097@qq.com");
        helper.setSentDate(new Date());
        helper.setSubject("邮件主题-测试邮件");
        /*指定freemarker的版本*/
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_30);
        /*类加载器和模板位置 --->  Freemarker需要配置模板位置（在template下也要配置） 而Freemarker默认是在template目录下*/
        cfg.setClassLoaderForTemplateLoading(ApplicationTests.class.getClassLoader(), "/mail");
        /*获取模板*/
        Template template = cfg.getTemplate("mail.ftl");
        User user = new User();
        user.setUsername("韩宝帅");
        user.setCompany("大森林公司");
        user.setPosition("Java架构师");
        user.setSalary(99999.0);
        StringWriter out = new StringWriter();
        /*内容对象和输出流*/
        template.process(user, out);
        System.out.println("out.toString() = " + out.toString());
        /*out.toString就是邮件内容 */
        helper.setText(out.toString(),true);
        javaMailSender.send(mimeMessage);
    }

    /*最佳方案：使用thymeleaf做为邮件模板*/
    @Autowired
    TemplateEngine templateEngine;
    @Test
    void test4() throws MessagingException, IOException, TemplateException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom("2458528193@qq.com");
        helper.setTo("2582883581@qq.com");
        helper.setSentDate(new Date());
        helper.setSubject("邮件主题-测试邮件");
        /*定义页面上下文内容*/
        Context ctx = new Context();
        ctx.setVariable("username", "hbs");
        ctx.setVariable("company", "大森林公司");
        ctx.setVariable("position", "Java架构师");
        ctx.setVariable("salary", "99999.0 ");
        /**
         * &#x53C2;&#x6570;&#xFF1A;
         *  template:&#x6A21;&#x677F;&#x540D;&#x79F0;
         *  Context:&#x4E0A;&#x4E0B;&#x6587;&#x53C2;&#x6570;
         *  &#x8FD4;&#x56DE;&#x6E32;&#x67D3;&#x7ED3;&#x679C; &#xFF1A;String
         */
        String text = templateEngine.process("mail.html", ctx);
        helper.setText(text,true);
        javaMailSender.send(mimeMessage);
    }

}
