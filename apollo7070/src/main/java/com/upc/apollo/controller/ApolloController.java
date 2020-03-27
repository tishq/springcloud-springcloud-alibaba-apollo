package com.upc.apollo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApolloController {
    @Value("${apollo}")
    private String configInfo;

    @GetMapping(value = "/apollo")
    public String getConfigInfo() {
        return configInfo;
    }
}
