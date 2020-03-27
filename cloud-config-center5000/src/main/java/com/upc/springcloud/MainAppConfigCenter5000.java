package com.upc.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableConfigServer
@EnableEurekaClient
public class MainAppConfigCenter5000 {
    public static void main(String[] args) {
        SpringApplication.run(MainAppConfigCenter5000.class, args);
    }
}
