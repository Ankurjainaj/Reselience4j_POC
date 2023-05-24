package com.resilience4j.CircuitBreaker.controller;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@RestController
@RequestMapping("/a")
@Slf4j
public class HelloWorldController {

    @Autowired
    private RestTemplate restTemplate;

    private static final String BASE_URL = "http://localhost:9091";

    private static final String SERVICE_A = "serviceA";
    int count = 1;

    @GetMapping
//    @CircuitBreaker(name = SERVICE_A, fallbackMethod = "serviceFallback")
//    @Retry(name = SERVICE_A)
    @RateLimiter(name = SERVICE_A)
    public String serviceA() {
        log.info("Retry method called for " + count + " times at " + new Date());
        String url = BASE_URL + "/b";
        return restTemplate.getForObject(url, String.class);
    }

    public String serviceFallback(Exception e) {
        return "This is a fallback method for Service A";

    }
}
