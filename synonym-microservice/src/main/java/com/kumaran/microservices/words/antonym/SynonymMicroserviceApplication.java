package com.kumaran.microservices.words.antonym;

import com.kumaran.microservices.words.config.DictionaryApiConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableCaching
@ComponentScan(basePackages = "com.kumaran.microservices.words")
public class SynonymMicroserviceApplication {

    private final DictionaryApiConfig dictionaryApiConfig;

    public SynonymMicroserviceApplication(DictionaryApiConfig dictionaryApiConfig) {
        this.dictionaryApiConfig = dictionaryApiConfig;
    }

    public static void main(String[] args) {
        SpringApplication.run(SynonymMicroserviceApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
