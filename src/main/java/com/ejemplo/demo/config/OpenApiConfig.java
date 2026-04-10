package com.ejemplo.demo.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI workshopOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Workshop Spring Boot API")
                        .version("1.0.0")
                        .description("API del taller de Spring Boot con validaciones, manejo de errores y documentación OpenAPI")
                        .contact(new Contact()
                                .name("Angello Escobar")
                                .email("aescobarg21@miumg.edu.gt")))
                .externalDocs(new ExternalDocumentation()
                        .description("Documentación del proyecto"));
    }
}