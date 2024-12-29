package com.javarbro.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

@Configuration
public class BeanConfig {

    @Bean(name = "movieInfoRestClient")
    public RestClient getMovieInfoRestClient() {
        return RestClient.builder().baseUrl("http://movie-info-service").build();
    }

    @Bean(name = "movieRatingsRestClient")
    public RestClient getMovieRatingsRestClient() {
        return RestClient.builder().baseUrl("http://movie-ratings-service").build();
    }

    @Bean(name = "restClient")
    public RestClient getRestClient() {
        return RestClient
                .builder()
                .requestFactory(getHttpComponentsClientHttpRequestFactory())
                .build();
    }

    @Bean
    public HttpComponentsClientHttpRequestFactory getHttpComponentsClientHttpRequestFactory() {
        HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        httpComponentsClientHttpRequestFactory.setConnectTimeout(5000);
        httpComponentsClientHttpRequestFactory.setConnectionRequestTimeout(5000);
        return httpComponentsClientHttpRequestFactory;
    }
}