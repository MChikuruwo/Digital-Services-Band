package zw.digitalservices.Digital.Services.Customer.config;

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

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("zw.digitalservices.Digital.Services.Customer.api"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());

    }

    private ApiInfo apiInfo(){
        return new ApiInfo(
                "Digital Services Customer Band REST API",
                "REST API to be used to opt-in/opt-out users on the digital services bands platform",
                "API Terms of Service",
                "Terms of service",
                new Contact("Munyaradzi Chikuruwo", "www.digitalservicesbands.co.zw", "mchikuruwo@hotmail.com"),
                "License of API", "API license URL", Collections.emptyList()
        );
    }

   
}
