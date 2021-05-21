package com.kumaran.microservices.words.microservice.rest;

import com.kumaran.microservices.words.microservice.service.WordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("word")
@Slf4j
public class WordRest {

    private final WordService wordService;

    public WordRest(WordService wordService) {
        this.wordService = wordService;
    }

    @GetMapping("/{inputWord}")
    public Mono<Map<String, List<String>>> getWordDetails(@PathVariable("inputWord") String inputWord) throws ExecutionException, InterruptedException, UnknownHostException {
        Map<String, List<String>> wordDetails = new HashMap<>();
        // ensure - 2 API requests are fired - in parallel.
        Mono<List<String>> synonymsSubscription = wordService.getSynonyms(inputWord);
        Mono<List<String>> antonymSubscription = wordService.getAntonyms(inputWord);
        log.info("Synonym and Antonym get requests fired from {}", InetAddress.getLocalHost().getHostName());
        // wait for both responses - and send them.
        return Mono.zip(synonymsSubscription, antonymSubscription, (synonyms, antonyms) -> {
            wordDetails.put("antonyms", antonyms);
            wordDetails.put("synonyms", synonyms);
            return wordDetails;
        });

    }
}
