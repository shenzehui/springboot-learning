package org.javaboy.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * 这个类的作用相当于web.xml配置文件
 *
 * @author szh
 */
public class WebInit implements WebApplicationInitializer {
    @Override
    public void onStartup(javax.servlet.ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.setServletContext(servletContext);
        //这里只加载了SpringMvc配置文件,没有加载到SpringConfig配置文件，因此扫描不到service层
        ctx.register(SpringMvcConfig.class);
        ServletRegistration.Dynamic springmvc = servletContext.addServlet("springmvc", new DispatcherServlet(ctx));
        springmvc.addMapping("/");
        springmvc.setLoadOnStartup(1);
    }
}
