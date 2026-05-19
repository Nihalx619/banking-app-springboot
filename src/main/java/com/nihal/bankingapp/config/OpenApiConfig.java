package com.nihal.bankingapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OpenApiConfig {
	@Bean
	public OpenAPI customOpenAPI() {
		
		

				return new OpenAPI()
					    .info(new Info()
					        .title("Banking App API")
					        .version("1.0")
					        .description("Built by Nihal"))
					    .addSecurityItem(new SecurityRequirement()
					        .addList("Bearer Authentication"))
					    .components(new Components()
					        .addSecuritySchemes("Bearer Authentication",
					            new SecurityScheme()
					                .type(SecurityScheme.Type.HTTP)
					                .scheme("bearer")
					                .bearerFormat("JWT")));
	}
}
