package com.gostech.swiftportbackend.shared.infrastructure.documentation.openapi.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

/**
 * OpenAPI Configuration
 * <p>
 * This class configures the OpenAPI documentation for the application.
 * It includes JWT Bearer token authentication configuration.
 * </p>
 */
@Configuration
public class OpenApiConfiguration {

    @Value("${documentation.application.description}")
    private String applicationDescription;

    @Value("${documentation.application.version}")
    private String applicationVersion;

    @Bean
    public OpenAPI swiftPortOpenApi() {
        // Define the security scheme for JWT
        var jwtSecurityScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .in(SecurityScheme.In.HEADER)
                .name("Authorization");

        // Define the security requirement
        var securityRequirement = new SecurityRequirement().addList("Bearer Authentication");

        // Configure contact information
        var contact = new Contact()
                .name("SwiftPort Development Team")
                .email("dev@gostech.com")
                .url("https://gostech.com");

        // Configure license information
        var mitLicense = new License()
                .name("MIT License")
                .url("https://choosealicense.com/licenses/mit/");

        // Configure API information
        var info = new Info()
                .title("SwiftPort Backend API")
                .version(applicationVersion)
                .contact(contact)
                .description(applicationDescription)
                .termsOfService("https://gostech.com/terms")
                .license(mitLicense);

        return new OpenAPI()
                .openapi("3.0.1")
                .info(info)
                .addSecurityItem(securityRequirement)
                .components(new Components().addSecuritySchemes("Bearer Authentication", jwtSecurityScheme));
    }
}