package com.upc.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MainAppConfigClient5001 {
    public static void main(String[] args) {
        SpringApplication.run(MainAppConfigClient5001.class, args);
    }
}
