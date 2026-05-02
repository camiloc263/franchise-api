package com.accenture.challenge.franchise.franchise_api.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@OpenAPIDefinition(
        info = @io.swagger.v3.oas.annotations.info.Info(
                title = "Franchise Management API",
                version = "1.0.0",
                description = "API reactiva para la gestión de franquicias, sucursales y productos.",
                contact = @io.swagger.v3.oas.annotations.info.Contact(
                        name = "Tu Nombre",
                        email = "ccamii@ejemplo.com"))
                    )
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Franchise Management API")
                        .version("1.0.0")
                        .description("API reactiva para la gestión de franquicias, sucursales y productos.")
                        .contact(new Contact()
                                .name("Tu Nombre")
                                .email("tu-email@ejemplo.com")));
    }
}