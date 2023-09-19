package com.rem.eshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
@EnableSwagger2
public class EshopApplication {

	public static void main(String[] args) {
		SpringApplication.run(EshopApplication.class, args);
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().paths(PathSelectors.ant("/api/*"))
				.apis(RequestHandlerSelectors.basePackage("com.rem.eshop.controllers")).build().apiInfo(apiDetails());
		/*
		 * .apiInfo(this.getApiInfo()).securitySchemes(this.securitySchemes())
		 * .securityContexts(Arrays.asList(this.securityContext()));
		 */

	}

	public SecurityContext securityContext() {
		AuthorizationScope[] scopes = { new AuthorizationScope("read", "for read operation"),
				new AuthorizationScope("write", "for write operation") };
		List<SecurityReference> securityReferences = Arrays.asList(new SecurityReference("basicAuth", scopes),
				new SecurityReference("Key", scopes), new SecurityReference("User Authentification Token", scopes));
		return SecurityContext.builder().securityReferences(securityReferences).forPaths(PathSelectors.any()).build();
	}

	public List<SecurityScheme> securitySchemes() {
		// SecurityScheme basicAuth = new BasicAuth("basicAuth");
		SecurityScheme userAuthToken = new ApiKey("User Authentification Token", "Authorization", "header");
		// SecurityScheme keyAuth = new ApiKey("Key", "Key", "header");
		return Arrays.asList(
				// keyAuth,
				userAuthToken
		// , basicAuth
		);
	}

	/*
	 * private ApiInfo getApiInfo() { Contact contact = new
	 * Contact("ECOFIN PRO PROJECT :: MINES-GAZ-ELECTRICITE",
	 * "https://ecofinpro.org", "contact@ecofinpro.org"); return new
	 * ApiInfoBuilder()
	 * .title("REST API FOR ECOFIN PRO PROJECT (FRONT END & BACK OFFICE :: MODULE MINES-GAZ-ELECTRICITE)"
	 * ) .description(
	 * "This page show all ressources that you could handle to communicate with database via REST API."
	 * ) .version("1.0").license("EcofinPro v.1.0").licenseUrl(
	 * "http://www.apache.org/licenses/LICENSE-2.0") .contact(contact).build(); }
	 */

	private ApiInfo apiDetails() {
		return new ApiInfo("REST API FOR e-SHOPPING APPLICATION",
				"REST API for e-shopping appication (built for fun with Spring boot)", "1.0", "Free to use",
				new springfox.documentation.service.Contact("Rémi Kpadonou", "https://remikpadonou.herokuapp.com",
						"rem.kpadonou@gmail.com"),
				"API License", "https://remikpadonou.herokuapp.com", Collections.emptyList());
	}

}