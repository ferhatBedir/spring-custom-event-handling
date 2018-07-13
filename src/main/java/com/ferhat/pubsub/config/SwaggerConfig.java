package com.ferhat.pubsub.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /*@Bean
    public Docket pubSubApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select().apis(RequestHandlerSelectors.basePackage("com.ferhat.pubsub")).build();
    }*/

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

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select().apis(RequestHandlerSelectors.any()).paths(PathSelectors.any()).build().apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo("PubSub Rest Api", "PubSub BackEnd App", "1.0.0",
                null,
                new Contact("Ferhat Bedir", "www.tmod.com.tr", "ferhat.bedir@hotmail.com"),
                null, null, Collections.emptyList());
    }
}
