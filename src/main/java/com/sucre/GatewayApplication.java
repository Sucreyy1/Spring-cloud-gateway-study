package com.sucre;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    public RouteLocator testRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("after_route", r -> r.path("/haixue/**")
                .uri("http://haixue.com"))
                .build();
    }
}
