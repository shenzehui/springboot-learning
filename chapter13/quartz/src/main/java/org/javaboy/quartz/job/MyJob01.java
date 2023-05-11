package org.javaboy.quartz.job;

import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author szh
 * @Date 2022/5/18 18:08
 * @PackageName:org.javaboy.quartz.job
 * @ClassName: MyJob01
 * @Description: 配置定时任务：方法一：定义一个普通的 JavaBean 加上 @Component 注解
 * @Version 1.0
 */
@Component
public class MyJob01 {
    public void sayHello() {
        System.out.println("MyJob01" + new Date());
    }

}
