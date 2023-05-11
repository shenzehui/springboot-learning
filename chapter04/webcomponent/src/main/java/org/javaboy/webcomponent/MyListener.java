package org.javaboy.webcomponent;

import org.springframework.web.context.request.RequestContextListener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.annotation.WebListener;

/**
 * @author szh
 */
@WebListener
public class MyListener extends RequestContextListener {

    @Override
    public void requestInitialized(ServletRequestEvent requestEvent) {
        System.out.println("requestInitialized");
    }

    @Override
    public void requestDestroyed(ServletRequestEvent requestEvent) {
        System.out.println("requestDestroyed");
    }
}
