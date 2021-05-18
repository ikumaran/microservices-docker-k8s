package com.kumaran.microservices.words.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "dictionary-api")
@Data
public class DictionaryApiConfig {

    private String url;
    private String key;

}
