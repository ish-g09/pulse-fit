package com.pulsefit.pulsefit.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("PulseFit Engine API")
                        .version("1.0")
                        .description("High-performance telemetry intake and real-time LLM athletic recovery intelligence engine.")
                        .contact(new Contact()
                                .name("PulseFit Engineering")
                                .email("contact@pulsefit.com")));
    }
}