package com.codebyarunyadav.book.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI customOpenAPI() {

		final String securitySchemeName = "bearerAuth";

		return new OpenAPI()

				// API Information
				.info(new Info().title("Books Management API").version("1.0.0")
						.description("REST APIs for managing Books Printing and Rating " + "using Spring Boot 3 and MySQL.")

						.contact(new Contact().name("HoxCloud Development Team").email("support@hoxcloud.com").url("https://hoxcloud.in"))

						.license(new License().name("Apache 2.0").url("https://www.apache.org/licenses/LICENSE-2.0")))
				.servers(
						List.of(new Server().url("http://localhost:8080/book-service").description("Local API Gateway"),
								new Server().url("http://172.26.234.58:8080/book-service").description("Docker API Gateway")))
				.addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
				.components(new Components().addSecuritySchemes(securitySchemeName,new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")));
	}
}