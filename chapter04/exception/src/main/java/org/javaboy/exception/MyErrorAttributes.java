package org.javaboy.exception;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * @author szh
 */
@Component
public class MyErrorAttributes extends DefaultErrorAttributes {
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        Map<String, Object> map = super.getErrorAttributes(webRequest, options);
        // 通过状态码来设置
        if ((Integer) map.get("status") == 404) {
            map.put("message", "页面不存在");
            // 建议这种方式定义数据
        } else if ((Integer) map.get("status") == 500) {
            map.put("message", "服务器内部错误");
        }
        return map;
    }
}
