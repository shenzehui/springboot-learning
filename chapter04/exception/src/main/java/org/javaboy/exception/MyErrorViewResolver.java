package org.javaboy.exception;

import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.DefaultErrorViewResolver;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author szh
 */
@Component
public class MyErrorViewResolver extends DefaultErrorViewResolver {

    /**
     * Create a new {@link DefaultErrorViewResolver} instance.
     *
     * @param applicationContext the source application context
     * @param resources          resource properties
     * @since 2.4.0
     */
    public MyErrorViewResolver(ApplicationContext applicationContext, WebProperties.Resources resources) {
        super(applicationContext, resources);
    }

    /**
     * 定义自定义异常返回视图路径
     *
     * @param request
     * @param status
     * @param model
     * @return
     */
    @Override
    public ModelAndView resolveErrorView(HttpServletRequest request, HttpStatus status, Map<String, Object> model) {
        // 这里的 map 不可修改 所以不建议这种方式
//        HashMap map = new HashMap();
//        map.putAll(model);
//        if((Integer)model.get("status") == 500){
//            map.put("message", "服务器内部错误");
//        }
        ModelAndView view = new ModelAndView("javaboy/999", model);
        return view;
    }
}
