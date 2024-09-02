package com.example.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

  @Bean
  public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
    return builder.routes()
      .route(r -> r.path("/api/employees/**")
        .uri("lb://employee-service"))
      .route(r -> r.path("/api/cars/**")
        .uri("lb://car-service"))
      .route(r -> r.path("/api/maintenances/**")
        .uri("lb://maintenance-service"))
      .build();
  }
}

