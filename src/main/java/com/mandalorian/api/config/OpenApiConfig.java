package com.mandalorian.api.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@OpenAPIDefinition
@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI(
        @Value("${openapi.service.title}") String serviceTitle,
        @Value("${openapi.service.version}") String serviceVersion,
        @Value("${openapi.service.url}") String url,
        @Value("${openapi.service.url-description}") String urlDescription
    ) {
        final String securitySchemeName = "bearerAuth";
        return new OpenAPI()
            .servers(List.of(new Server().url(url).description(urlDescription)))
            .components(
                new Components()
                    .addSecuritySchemes(
                        securitySchemeName,
                        new SecurityScheme()
                            .name("Bearer Authorization")
                            .description("JWT Bearer Token Authorization")
                            .scheme("bearer")
                            .type(SecurityScheme.Type.HTTP)
                            .bearerFormat("JWT")
                            .in(SecurityScheme.In.HEADER)
                    )
            )
            .security(List.of(new SecurityRequirement().addList(securitySchemeName)))
            .info(
                new Info()
                    .title(serviceTitle)
                    .version(serviceVersion)
                    .description(String.format("Documentación para el %s", serviceTitle))
                    .license(new License().name("Info Licencia API").url("https://www.librosdelmandalorian.com"))
                    .termsOfService("https://www.librosdelmandalorian.com/terminos-y-condiciones")
                    .contact(new Contact().name("Francisco Ugalde").email("hola@librosdelmandalorian.com").url("https://librosdelmandalorian.com"))
            );
    }
}