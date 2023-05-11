package org.javaboy.cacheredis;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author szh
 */
@Component
public class MyKeyGenerator implements KeyGenerator {

    /**
     * 自定义key
     *
     * @param target @Cacheable  所在类的名称
     * @param method @Cacheable 方法名称
     * @param params 方法参数
     * @return
     */
    @Override
    public Object generate(Object target, Method method, Object... params) {
        // 每次都会进入缓存 toString 每次都不一样
        String s = target.toString() + ":" + method.getName() + ":" + Arrays.toString(params);
        return s;
    }
}
