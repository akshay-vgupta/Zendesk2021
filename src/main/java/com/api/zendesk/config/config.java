package com.api.zendesk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Configuration
public class config {

    @Autowired
    public Environment env;


     //System.out.println("username is "+env.getProperty("JAVA_HOME"));
    //String username = env.getProperty("ZEN_USER");


    //String password = env.getProperty("ZEN_PASSWORD");
    //Authentication to access zendesk account
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {


        return builder.basicAuthentication(env.getProperty("ZEN_USER"),
                env.getProperty("ZEN_PASSWORD"))
                .build();
    }

}