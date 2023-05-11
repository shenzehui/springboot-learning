package org.javabot.securitydemo.controller;

import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @Author szh
 * @Date 2022/5/20 15:04
 * @PackageName:org.javabot.securitydemo.controller
 * @ClassName: HelloController
 * @Description: TODO
 * @Version 1.0
 */
@RestController
public class HelloController {

    @Autowired
    Producer producer;

    @GetMapping("/hello")
    public String hello(){
        return "hello security";
    }

    @GetMapping("/admin")
    public String admin(){
        return "hello admin";
    }

    @GetMapping("/vf")
    public void getVerifyCode(HttpServletResponse response, HttpSession session) throws IOException {
        WebAuthenticationDetails details = (WebAuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
        String remoteAddress = details.getRemoteAddress();
        String sessionId = details.getSessionId();
        System.out.println("remoteAddress = " + remoteAddress);
        System.out.println("sessionId = " + sessionId);
        /*返回的文件格式*/
        response.setContentType("image/jpeg");
        /*验证码生成文本*/
        String text = producer.createText();
        /*存到session中*/
        session.setAttribute("vf", text);
        /*生成图片*/
        BufferedImage bi = producer.createImage(text);

        /*以流的形式写出图片  特点：会自动关闭流*/
        try (ServletOutputStream out = response.getOutputStream()){
            /*ImageIO.write
                参数： RenderedImage 接口  实现类:BufferedImage
                      formatName 图片格式
                      OutPutStream 输出流  继承类:ServletOutputStream
            */
            ImageIO.write(bi, "jpeg",out );
        }

    }
}
