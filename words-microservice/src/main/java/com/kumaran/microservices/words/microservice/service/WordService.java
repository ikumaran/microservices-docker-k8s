package com.kumaran.microservices.words.microservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@Slf4j
public class WordService {

    private final WebClient webClient;

    public WordService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public Mono<List<String>> getSynonyms(String inputWord) {
        return webClient.get()
                .uri("http://SYNONYM-MICROSERVICE/synonym/{inputWord}", inputWord)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<>() {});
    }

    public Mono<List<String>> getAntonyms(String inputWord) {
        return webClient.get()
                .uri("http://ANTONYM-MICROSERVICE/antonym/{inputWord}", inputWord)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<>() {});
    }
}
