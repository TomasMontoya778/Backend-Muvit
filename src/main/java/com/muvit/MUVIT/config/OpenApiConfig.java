package com.muvit.MUVIT.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "Api MUVIT",
        version = "1.0",
        description = "This API was created to manage the MUVIT users, drivers, and admins"
    )
)
public class OpenApiConfig {

}
