package org.javaboy.swagger3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @Author szh
 * @Date 2022/5/18 19:09
 * @PackageName:org.javaboy.swagger3.config
 * @ClassName: SwaggerConfig
 * @Description: TODO
 * @Version 1.0
 */
@Configuration
public class SwaggerConfig {
    @Bean
    Docket docket() {
        // 最新版3.0
        return new Docket(DocumentationType.OAS_30)
                .select()
                // 指定controller路径
                .apis(RequestHandlerSelectors.basePackage("org.javaboy.swagger3.controller"))
                // 是否都要生成
                .paths(PathSelectors.any())
                .build()
                .apiInfo(
                        new ApiInfoBuilder()
                                .description("vhr 项目接口文档")
                                .contact(new Contact("javaboy", "http://www.itboyhub.com", "jnydy@qq.com"))
                                .version("v1.0")
                                .title("API 测试文档")
                                .license("Apache2.0")
                                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                                .build()
                );
    }
}
