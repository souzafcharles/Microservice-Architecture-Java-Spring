package com.souza.charles.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfiguration {

    private static final String ACCOUNT_SERVICE = "lb://account-service";
    private static final String TRANSACTION_SERVICE = "lb://transaction-service";

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("customer-route", p -> p.path("/customers/**").uri(ACCOUNT_SERVICE))
                .route("transaction-route", p -> p.path("/transactions/**").uri(TRANSACTION_SERVICE))
                .route("transfer-route", p -> p.path("/transfers/**").uri(TRANSACTION_SERVICE))
                .build();
    }
}