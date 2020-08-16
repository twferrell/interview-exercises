package com.asurint.slug.swagger

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType.SWAGGER_2
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
class SwaggerConfiguration {
	@Bean
	fun api(): Docket = Docket(SWAGGER_2)
		.select()
		.apis(RequestHandlerSelectors.basePackage("com.asurint.slug.api"))
		.paths(PathSelectors.any())
		.build()
		.apiInfo(apiInfo())

	@Bean
	fun apiInfo(): ApiInfo {
		return ApiInfoBuilder()
			.title("Slug API Documentation")
			.version("1.0.0")
			.description("Interactive API documentation")
			.build()
	}
}