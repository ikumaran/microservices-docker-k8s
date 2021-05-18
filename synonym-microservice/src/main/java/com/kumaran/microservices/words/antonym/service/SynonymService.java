package com.kumaran.microservices.words.antonym.service;

import com.kumaran.microservices.words.service.DictionaryApiService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class SynonymService {

    private final DictionaryApiService dictionaryApiService;

    public SynonymService(DictionaryApiService dictionaryApiService) {
        this.dictionaryApiService = dictionaryApiService;
    }

    @Cacheable(value = "synonyms")
    public List<String> getSynonyms(String inputWord) {
        LinkedHashMap<String, LinkedHashMap<String, Object>>[] responseArray = dictionaryApiService.getResponse(inputWord);
        List<String> synonyms = new ArrayList<>();
        for (LinkedHashMap<String, LinkedHashMap<String, Object>> response : responseArray) {
            LinkedHashMap<String, Object> metadata = response.get("meta");
            List<List<String>> synonymLists = (List<List<String>>) metadata.get("syns");
            synonymLists.forEach(synonyms::addAll);
        }
        return synonyms;
    }
}
