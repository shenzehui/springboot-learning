package org.javaboy.idempontent.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解
 *
 * @author szh
 */
@Target(ElementType.METHOD) // 表示注解加在方法上
@Retention(RetentionPolicy.RUNTIME) // 保存在runtime时间
public @interface AutoIdempotent {


}
