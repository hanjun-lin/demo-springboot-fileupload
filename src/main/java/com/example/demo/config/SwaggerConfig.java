package com.example.demo.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Value("${swagger.enabled}")
	private Boolean enabledSwagger;

	@Bean
	public Docket createRestAPI() {
		return new Docket(DocumentationType.SWAGGER_2)
			.enable(enabledSwagger)
			.apiInfo(apiInfo())
			.select()
			.apis(RequestHandlerSelectors.basePackage("com.example.demo.controller"))
			.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
			.paths(PathSelectors.any())
			.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
			.title("Demo API Server")
			.description("This is the online api documentation for demo project.")
//			.termsOfServiceUrl("")
//			.contact(new Contact("Contact Name", "Contact URL", "Contact Email"))
			.version("0.0.1")
			.build();
	}
}