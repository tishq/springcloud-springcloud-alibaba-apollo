package com.upc.apollo;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableApolloConfig
public class MainApollo7070 {
    public static void main(String[] args) {
        SpringApplication.run(MainApollo7070.class, args);
    }
}

