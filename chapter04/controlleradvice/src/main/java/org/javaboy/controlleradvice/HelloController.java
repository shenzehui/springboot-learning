package org.javaboy.controlleradvice;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Set;

/**
 * @author szh
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(Model model) {
        // 获取预设全局数据 通过Model获取  //根据主键获取map
        Map<String, Object> map = (Map<String, Object>) model.asMap().get("info");
        // 获取Map中key集合
        Set<String> keySet = map.keySet();
        for (String key : keySet) {
            System.out.println(key + ":" + map.get(key));
        }
        return "hello";
    }
}
