package com.example.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class FilterConfig {

//  @Bean
  public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
    return builder
        .routes()
        .route(r -> r.path("/first-service/**")
            .filters(f -> f.addRequestHeader("first-request", "header-request-value1")
                .addResponseHeader("first-response", "header-response-value1"))
            .uri("http://localhost:8081"))
        .route(r -> r.path("/second-service/**")
            .filters(f -> f.addRequestHeader("second-request", "header-request-value2")
                .addResponseHeader("second-response", "header-response-value2"))
            .uri("http://localhost:8082"))
        .build();
  }
}
