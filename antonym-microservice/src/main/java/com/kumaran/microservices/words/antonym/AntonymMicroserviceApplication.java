package com.kumaran.microservices.words.antonym;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableCaching
@ComponentScan(basePackages = "com.kumaran.microservices.words")
public class AntonymMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AntonymMicroserviceApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
