package com.example.Resilience.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI blobOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Blob Manager API")
                        .description("# # This microservice is responsible for communication with blob storage. All data saved to blob storage went through this microservice."));
    }

}
