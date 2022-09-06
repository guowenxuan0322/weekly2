package com.zdpx.weekly.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;



@Configuration
@EnableSwagger2
public class SwaggerConfig {
    /**
     * -swagger2的配置文件
     * <pre>
     * -这里可以配置swagger2的一些基本的内容，比如扫描的包等等
     * </pre>
     *
     * @return
     * @since 0.1
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.zdpx.weekly.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * api文档的详细信息函数,注意这里的注解引用的是哪个
     *
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // //大标题
                .title("weekly RESTful API")
                // 版本号
                .version("1.0")
                // 描述
                .description("API 描述")
                //作者
                // .license("The Apache License, Version 2.0")
                // .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .build();
    }
}