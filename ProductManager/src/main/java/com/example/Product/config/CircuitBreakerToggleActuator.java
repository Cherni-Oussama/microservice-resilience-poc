package com.example.Product.config;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id = "circuitBreakersToggle")
public class CircuitBreakerToggleActuator {

    private final CircuitBreakerRegistry circuitBreakerRegistry;

    public CircuitBreakerToggleActuator(CircuitBreakerRegistry circuitBreakerRegistry) {
        this.circuitBreakerRegistry = circuitBreakerRegistry;
    }

    @ReadOperation
    public CircuitBreaker.State toggleCircuitBreaker(@Selector String name) {
        if(!circuitBreakerRegistry.circuitBreaker(name).getState().equals(CircuitBreaker.State.DISABLED)) {
            circuitBreakerRegistry.circuitBreaker(name).transitionToDisabledState();
        } else {
            circuitBreakerRegistry.circuitBreaker(name).transitionToClosedState();
        }
        return circuitBreakerRegistry.circuitBreaker(name).getState();
    }
}

