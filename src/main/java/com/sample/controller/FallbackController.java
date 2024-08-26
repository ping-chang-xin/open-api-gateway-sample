package com.sample.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
public class FallbackController {

    @RequestMapping("/rateLimitFallback")
    public Mono<String> rateLimitFallback(ServerWebExchange exchange){
        exchange.getResponse().setStatusCode(HttpStatus.TOO_MANY_REQUESTS);
        return Mono.just("too many requests, please try again later.");
    }

}
