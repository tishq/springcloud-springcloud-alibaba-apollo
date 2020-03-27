package com.upc.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class TestMain9002 {
    public static void main(String[] args) {
        SpringApplication.run(TestMain9002.class, args);
    }
}
