package org.javaboy.https.config;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author szh
 */
@Configuration
public class TomcatConfig {
    @Bean
    TomcatServletWebServerFactory tomcatServletWebServerFactory() {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory() {
            @Override
            protected void postProcessContext(Context context) {
                SecurityConstraint securityConstraint = new SecurityConstraint();
                securityConstraint.setUserConstraint("CONFIDENTIAL"); //设置保密
                SecurityCollection collection = new SecurityCollection();
                collection.addPattern("/*");
                securityConstraint.addCollection(collection);
                context.addConstraint(securityConstraint);
            }
        };
        factory.addAdditionalTomcatConnectors(myConnectors());
        return factory;
    }

    private Connector myConnectors() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        // 设置协议
        connector.setScheme("http");
        // 设置端口号
        connector.setPort(8081);
        connector.setSecure(false);
        connector.setRedirectPort(8080);
        return connector;
    }
}
