package com.upc.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope //动态刷新客户端配置 （还需手动 curl -X POST "http://localhost:5001/actuator/refresh"）
public class ConfigController {
    @Value("${aa}") //aa对应config-dev.yml里面的键
    private String configInfo;

    @GetMapping("/configInfo")
    public String getConfigInfo() {
        return configInfo;
    }
}
