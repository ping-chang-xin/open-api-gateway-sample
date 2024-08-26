package com.sample.ratelimiter;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.cloud.gateway.filter.ratelimit.AbstractRateLimiter;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.HashMap;

@Component
@Primary
public class ProductRateLimiter extends AbstractRateLimiter<ProductRateLimiter.Config> {

    private final RateLimiter rateLimiter = RateLimiter.create(1);

    public ProductRateLimiter() {
        super(Config.class, "product-rate-limiter", null);
    }

    @Override
    public Mono<Response> isAllowed(String routeId, String id) {
        if (rateLimiter.tryAcquire()) {
            return Mono.just(new Response(false, new HashMap<>()));
        }
        return Mono.just(new Response(true, new HashMap<>()));
    }

    static class Config {

    }

}
