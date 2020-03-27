package com.upc.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class NacosClientController {
    @Value("${aa}")
    private String configInfo;

    @GetMapping(value = "/nacos")
    public String getConfigInfo() {
        return "NacosConfigClient"+configInfo;
    }
}
