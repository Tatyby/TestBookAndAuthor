package com.example.testbooksandauthor.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "book and author service",
                description = "book and author service", version = "1",
                contact = @Contact(
                        name = "Buanova Tatyana",
                        email = "in_sdo@mail.ru"
                )
        )
)
public class OpenApiConfig {
}
