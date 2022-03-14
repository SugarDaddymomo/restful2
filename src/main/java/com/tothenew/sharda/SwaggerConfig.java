package com.tothenew.sharda;

import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.ant("/api/*"))
				.apis(RequestHandlerSelectors.basePackage("com.tothenew.sharda"))
				.build().apiInfo(apiDetails());
	}
	
	private ApiInfo apiDetails() {
		return new ApiInfo(
				"RESTful Assignment API",
				"Sample API from Sharda Kumari",
				"1.0",
				"Free to use",
				new springfox.documentation.service.Contact("Sharda Kumari", "https://www.tothenew.com/.com", "sharda.kumari@tothenew.com"),
				"API License",
				"https://www.tothenew.com/",
				Collections.emptyList());
	}
}