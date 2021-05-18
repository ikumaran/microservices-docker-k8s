package com.kumaran.microservices.words.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpServerErrorException;

public class DictionaryApiException extends HttpServerErrorException {
    public DictionaryApiException(HttpStatus statusCode) {
        super(statusCode);
    }

    public DictionaryApiException(HttpStatus statusCode, String statusText) {
        super(statusCode, statusText);
    }

}
