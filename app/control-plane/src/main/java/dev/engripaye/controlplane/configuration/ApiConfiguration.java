package dev.engripaye.controlplane.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiConfiguration {
    @Bean
    OpenAPI aegisForgeOpenApi() {
        return new OpenAPI().info(new Info()
                .title("AegisForge API")
                .description("Developer security control-plane API")
                .version("v1"));
    }
}
