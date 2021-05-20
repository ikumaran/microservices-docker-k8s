package com.kumaran.microservices.words.antonym.rest;

import com.kumaran.microservices.words.antonym.service.AntonymService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("antonym")
public class AntonymRest {

    private final AntonymService AntonymService;

    public AntonymRest(AntonymService AntonymService) {
        this.AntonymService = AntonymService;
    }

    @GetMapping("/{inputWord}")
    public List<String> getAntonyms(@PathVariable("inputWord") String inputWord) {
        return AntonymService.getAntonyms(inputWord);
    }
}
