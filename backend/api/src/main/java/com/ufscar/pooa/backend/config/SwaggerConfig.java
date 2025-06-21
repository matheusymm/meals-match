package com.ufscar.pooa.backend.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Meals Match API", version = "1"), servers = @Server(url = "http://localhost:8080", description = "Local Server"))
public class SwaggerConfig {

}
