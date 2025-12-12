package com.example.k8s;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class CustomRestClient {

    @Bean
    RestClient restClient() {
        return RestClient.builder().build();
    }

}
