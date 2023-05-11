package org.javaboy.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author szh
 * @Date 2022/5/18 17:50
 * @PackageName:org.javaboy.schedule
 * @ClassName: MySchedule
 * @Description: TODO
 * @Version 1.0
 */
@Component
public class MySchedule {
    /**
     * 表示当前任务结束以后，定一个定时任务，每1秒打印一次，是方法执行完毕之后过1秒执行，方法必然执行完毕
     * 下个任务开启点是前面一个任务执行完毕
     */
    @Scheduled(fixedDelay = 1000)
    public void fixedDelay() {
//        System.out.println("fixedDelay" + new Date());
    }

    /**
     * fixeRate:表示当前任务开始一秒中之后，下一个任务开启，可能这个任务还没有执行完，取决方法执行时间
     * 下个任务开启点是前面一个任务开始执行
     */
    @Scheduled(fixedRate = 1000)
    public void fixedRate() {
//        System.out.println("fixedRate" + new Date());
    }

    /**
     * initialDelay 表示首次任务的延迟时间，执行首次任务会延迟一秒启动
     */
    @Scheduled(initialDelay = 1000, fixedRate = 1000)
    public void initDelay() {
//        System.out.println("initDelay" + new Date());
    }

    /**
     * 满足cron表达式  年可以省略 顺序：秒 分 时 日 周 月 年  每5秒打印一次
     */
    @Scheduled(cron = "0/5 * * * * *")
    public void cron() {
        System.out.println("cron" + new Date());
    }

}
