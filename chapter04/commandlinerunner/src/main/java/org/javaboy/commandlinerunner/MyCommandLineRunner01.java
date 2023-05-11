package org.javaboy.commandlinerunner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author szh
 */
@Component
@Order(100) // 指定优先级，数字越小，优先级越大
public class MyCommandLineRunner01 implements CommandLineRunner {

    /**
     * 当系统启动时，run方法会被触发，方法参数就是main方法所传入的参数
     */
    @Override
    public void run(String... args) throws Exception {
        System.out.println("args1=" + Arrays.toString(args));
    }
}
