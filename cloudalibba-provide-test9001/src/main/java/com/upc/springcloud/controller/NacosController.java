package com.upc.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NacosController {
    @Value("{server.port}")
    private String serverPort;

    @GetMapping(value = "/nacos")
    public String getPort() {
        return "nacos客户端口"+serverPort;
    }
}
