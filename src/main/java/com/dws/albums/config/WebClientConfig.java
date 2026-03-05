package com.dws.albums.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${album.api.url}")
    private String albumApiUrl;

    @Bean
    public WebClient albumsWebClient(WebClient.Builder builder) {
        return builder
                .baseUrl(albumApiUrl)
                .build();
    }

    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }
}
