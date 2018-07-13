package com.ferhat.pubsub.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket pubSubApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select().apis(RequestHandlerSelectors.basePackage("com.ferhat.pubsub")).build();
    }

    /*
    return new Docket(DocumentationType.SWAGGER_2)
                .select().apis(RequestHandlerSelectors.basePackage("com.ferhat.pubsub"))
                .paths(regex("/user.*")).build();
       You can only see user controller and user objects with this config.
     */
    //-----------------------------------------------------------------------------
    /*
    return new Docket(DocumentationType.SWAGGER_2)
                .select().apis(RequestHandlerSelectors.basePackage("com.ferhat.pubsub"))
                .paths(regex("/department.*")).build();
       Only user controller and user objects view.
       You can see department controller and department object with this config.
     */
    //-----------------------------------------------------------------------------
    /*
	https://dzone.com/articles/spring-boot-restful-api-documentation-with-swagger swagger info
	 */
}
