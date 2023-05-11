package org.javaboy.commandlinerunner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author szh
 */
@Component
@Order(99)
public class MyCommandLineRunner02 implements CommandLineRunner {

    /**
     * 当系统启动时，run方法会被触发，方法参数就是main方法所传入的参数
     */
    @Override
    public void run(String... args) throws Exception {
        System.out.println("args2=" + Arrays.toString(args));
    }
}
