package org.javaboy.quartz.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;

/**
 * @Author szh
 * @Date 2022/5/18 18:10
 * @PackageName:org.javaboy.quartz.job
 * @ClassName: MyJob02
 * @Description: 方法二；继承 QuartzJobBean 实现方法
 * @Version 1.0
 */
public class MyJob02 extends QuartzJobBean {

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        System.out.println("MyJob02" + name + ":" + new Date());
    }
}
