package com.celonis.tasks.tasks.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
//import org.springdoc.core.builders.ApiInfoBuilder;  // Correct import for ApiInfoBuilder
//import org.springdoc.core.models.ApiInfo;  // Correct import for ApiInfo

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)  // OAS_30 for OpenAPI 3.0
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.celonis.tasks.tasks"))  // Update base package
                .paths(PathSelectors.any())  // Path filter, you can refine this
                .build()
                .apiInfo(new ApiInfoBuilder().title("Task API")
                        .description("API for managing tasks")
                        .version("1.0")
                        .build());
    }
}



//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//@Configuration
//@EnableSwagger2
//public class SwaggerConfig {
//
//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.celonis.tasks.tasks"))
//                .paths(PathSelectors.any())
//                .build()
//                .apiInfo(new ApiInfoBuilder().title("Task API")
//                        .description("API for managing tasks")
//                        .version("1.0")
//                        .build());
//    }
//}
