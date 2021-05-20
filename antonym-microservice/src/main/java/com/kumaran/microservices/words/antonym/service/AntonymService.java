package com.kumaran.microservices.words.antonym.service;

import com.kumaran.microservices.words.service.DictionaryApiService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class AntonymService {

    private final DictionaryApiService dictionaryApiService;

    public AntonymService(DictionaryApiService dictionaryApiService) {
        this.dictionaryApiService = dictionaryApiService;
    }

    @Cacheable(value = "antonyms")
    public List<String> getAntonyms(String inputWord) {
        LinkedHashMap<String, LinkedHashMap<String, Object>>[] responseArray = dictionaryApiService.getResponse(inputWord);
        List<String> antonyms = new ArrayList<>();
        for (LinkedHashMap<String, LinkedHashMap<String, Object>> response : responseArray) {
            LinkedHashMap<String, Object> metadata = response.get("meta");
            List<List<String>> antonymLists = (List<List<String>>) metadata.get("ants");
            antonymLists.forEach(antonyms::addAll);
        }
        return antonyms;
    }
}
