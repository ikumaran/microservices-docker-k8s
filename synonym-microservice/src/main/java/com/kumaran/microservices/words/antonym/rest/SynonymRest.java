package com.kumaran.microservices.words.antonym.rest;

import com.kumaran.microservices.words.antonym.service.SynonymService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("synonym")
public class SynonymRest {

    private final SynonymService synonymService;

    public SynonymRest(SynonymService synonymService) {
        this.synonymService = synonymService;
    }

    @GetMapping("/{inputWord}")
    public List<String> getSynonyms(@PathVariable("inputWord") String inputWord) {
        return synonymService.getSynonyms(inputWord);
    }
}
