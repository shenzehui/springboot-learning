package org.javaboy.controlleradvice;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.HashMap;
import java.util.Map;

/**
 * @author szh
 */
@ControllerAdvice
public class GlobalData {
    /**
     * 预设全局数据
     * 在 Controller 中都能获取这样的数据
     *
     * @return
     */
    @ModelAttribute(value = "info")  // 这里key是info value是 map // 不取名则为map返回的数据名
    public Map<String, Object> mydata() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "javaboy");
        map.put("address", "www.javaboy.org");
        return map;
    }

    @ModelAttribute(value = "data")
    public Map<String, Object> mydata1() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "szh");
        map.put("address", "www.szh.org");
        return map;
    }

    // 请求参数预处理
    // 区分前端传参时同属性名的情况

    /**
     * 与 Author 绑定
     *
     * @param binder
     */
    @InitBinder("a")
    public void initA(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("a.");
    }

    /**
     * 与 Book 绑定
     *
     * @param binder
     */
    @InitBinder("b")
    public void initB(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("b.");
    }
}
