package com.aditya.demo.controller.helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.util.Optional;

public class JsonHelper {

    public static Optional<String> toJson(Object obj) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            SimpleModule simpleModule = new SimpleModule();
            objectMapper.registerModule(simpleModule);
            String jsonInString = objectMapper.writeValueAsString(obj);
            return Optional.of(jsonInString);
        } catch(Exception e) {
            return Optional.empty();
        }
    }
}
