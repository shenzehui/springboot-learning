package org.javabot.securitydemo.config;

import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @Author szh
 * @Date 2022/5/20 15:31
 * @PackageName:org.javabot.securitydemo.config
 * @ClassName: VerifyCodeConfig
 * @Description: 验证码配置类
 * @Version 1.0
 */
@Configuration
public class VerifyCodeConfig {
    @Bean
    Producer verifyCode(){
        Properties properties = new Properties();
        /*配置*/
        /*验证码宽度*/
        properties.setProperty("kaptcha.image.width", "150");
        /*验证码高度*/
        properties.setProperty("kaptcha.image.height", "50");
        /*验证码文本  举例出来 */
        properties.setProperty("kaptcha.textproducer.char.string", "0123456789");
        /*验证码每次生成字符*/
        properties.setProperty("kaptcha.textproducer.char.length", "4");
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(new Config(properties));
        return defaultKaptcha;
    }
}
