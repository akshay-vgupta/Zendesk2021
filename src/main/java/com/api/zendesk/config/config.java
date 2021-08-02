package com.api.zendesk.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class config {


    Dotenv dotenv = Dotenv
            .configure()
            .load();

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {


        return builder.basicAuthentication(dotenv.get("ZEN_USER"),
                dotenv.get("ZEN_PASSWORD"))
                .build();
    }

}