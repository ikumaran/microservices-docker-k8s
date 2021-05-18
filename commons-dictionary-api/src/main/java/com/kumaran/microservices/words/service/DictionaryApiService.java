package com.kumaran.microservices.words.service;

import com.kumaran.microservices.words.exception.DictionaryApiException;
import com.kumaran.microservices.words.config.DictionaryApiConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;

@Slf4j
@Service
public class DictionaryApiService {

    private final DictionaryApiConfig dictionaryApiConfig;
    private final RestTemplate restTemplate;

    public DictionaryApiService(DictionaryApiConfig dictionaryApiConfig, RestTemplate restTemplate) {
        this.dictionaryApiConfig = dictionaryApiConfig;
        this.restTemplate = restTemplate;
    }

    public LinkedHashMap<String, LinkedHashMap<String, Object>>[] getResponse(String inputWord) {
        ResponseEntity<LinkedHashMap[]> responseEntity = restTemplate.getForEntity(getRequestUrl(inputWord),
                LinkedHashMap[].class);
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            log.info("Successfull response fom Dictionary API");
            return responseEntity.getBody();
        }
        throw new DictionaryApiException(responseEntity.getStatusCode(), "Unable to get response from Dictionary API");
    }

    private String getRequestUrl(String inputWord) {
        return this.dictionaryApiConfig.getUrl() + inputWord + "?key=" + dictionaryApiConfig.getKey();
    }

}
