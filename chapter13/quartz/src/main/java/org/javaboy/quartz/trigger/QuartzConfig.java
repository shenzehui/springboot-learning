package org.javaboy.quartz.trigger;

import org.javaboy.quartz.job.MyJob02;
import org.quartz.JobDataMap;
import org.quartz.SimpleTrigger;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ScheduledExecutorFactoryBean;
import org.springframework.scheduling.quartz.*;

import javax.xml.ws.WebEndpoint;

/**
 * @Author szh
 * @Date 2022/5/18 18:13
 * @PackageName:org.javaboy.quartz.trigger
 * @ClassName: QuartzConfig
 * @Description: 配置触发器
 * @Version 1.0
 */
@Configuration
public class QuartzConfig {

    /**
     * 两种定时任务的定义对应两种定时任务的配置
     *
     * @return
     */
    @Bean
    MethodInvokingJobDetailFactoryBean jobDetail01() {
        MethodInvokingJobDetailFactoryBean bean = new MethodInvokingJobDetailFactoryBean();
        // 配置 jobBean 的名称  类名首字母小写
        bean.setTargetBeanName("myJob01");
        // 配置目标方法名字 （这里因为是一个普通的JavaBean，注入到了Spring 容器中，要指定方法名称）
        bean.setTargetMethod("sayHello");
        return bean;
    }

    @Bean
    JobDetailFactoryBean jobDetail02() {
        JobDetailFactoryBean bean = new JobDetailFactoryBean();
        // 指定 jobClass
        bean.setJobClass(MyJob02.class);
        JobDataMap map = new JobDataMap();
        map.put("name", "javaboy");
        bean.setJobDataMap(map);
        return bean;
    }

    /**
     * trigger 触发器
     * 简单标准触发器
     */
    @Bean
    SimpleTriggerFactoryBean simpleTriggerFactoryBean() {
        SimpleTriggerFactoryBean bean = new SimpleTriggerFactoryBean();
        // 设置触发器对应的job
        bean.setJobDetail(jobDetail01().getObject());
        // 设置重复次数
        bean.setRepeatCount(3);
        // 设置启动延迟
        bean.setStartDelay(1);
        // 设置重复的间隔时间
        bean.setRepeatInterval(1000);
        return bean;
    }

    /**
     * 支持 cron 表达式
     *
     * @return
     */
    @Bean
    CronTriggerFactoryBean cronTriggerFactoryBean() {
        CronTriggerFactoryBean bean = new CronTriggerFactoryBean();
        // 指定 job
        bean.setJobDetail(jobDetail02().getObject());
        // 设置 cron 表达式
        bean.setCronExpression("0/5 * * * * ?");
        return bean;
    }

    /**
     * 配置启动器
     */
    @Bean
    SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        // 设置触发器
        bean.setTriggers(simpleTriggerFactoryBean().getObject(), cronTriggerFactoryBean().getObject());
        return bean;
    }
}
