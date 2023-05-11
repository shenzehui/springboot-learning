package org.javaboy.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author szh
 */
@Component
@Aspect
public class LogAspect {
    @Pointcut("execution(* org.javaboy.aop.service.*.*(..))")
    public void pc1() {

    }

    @Before("pc1()")
    public void before(JoinPoint jp) {
        // 获取方法名
        String name = jp.getSignature().getName();
        System.out.println(name + "方法开始执行了...");
    }

    @After("pc1()")
    public void after(JoinPoint jp) {
        String name = jp.getSignature().getName();
        System.out.println(name + "方法结束了...");
    }

    /**
     * 返回通知
     *
     * @param jp
     * @param s  返回值
     */
    @AfterReturning(value = "pc1()", returning = "s")
    public void afterReturning(JoinPoint jp, String s) {
        String name = jp.getSignature().getName();
        System.out.println(name + "方法的返回值时" + s);
    }

    /**
     * 异常通知
     *
     * @param jp
     * @param e  抛出异常
     */
    @AfterThrowing(value = "pc1()", throwing = "e")
    public void afterThrowing(JoinPoint jp, Exception e) {
        String name = jp.getSignature().getName();
        System.out.println(name + "方法抛出了异常：" + e.getMessage());
    }

    /**
     * 环绕通知
     *
     * @param pjp
     * @return
     */
    @Around(value = "pc1()")
    public Object around(ProceedingJoinPoint pjp) {
        String name = pjp.getSignature().getName();
        try {
            // 前置代码...（前置通知）  类似于反射中的 invoke 方法，proceed 为方法返回值（返回通知）
            Object result = pjp.proceed();
            System.out.println("result = " + result);
            // 后置代码...（后置通知）
            return result;
        } catch (Throwable e) {
            // 这里如果抛出异常，相当于异常通知
            System.out.println(name + "方法抛出了异常：" + e.getMessage());
        }
        return null;
    }

}
