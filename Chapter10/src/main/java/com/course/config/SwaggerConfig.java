package com.course.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configurable
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket commonApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .pathMapping("/")
                .select()//选择那些路径和api会生成documen
                .apis(RequestHandlerSelectors.any())//对所有API进行监控
                .paths(PathSelectors.regex("/.*"))// 对根下所有路径进行监控
                .build();
    }

    private ApiInfo apiInfo(){
        System.out.println("执行了嘛？");
        return new ApiInfoBuilder().title("我的接口文档")
                .contact(new Contact("yx","","1780576729@qq.com"))
                .description("这是我的swaggerui生成的生成的接口文档")
                .version("1.0.0")
                .build();
    }
}
