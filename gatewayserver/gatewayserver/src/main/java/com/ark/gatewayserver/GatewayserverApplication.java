package com.ark.gatewayserver;

import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.factory.AddResponseHeaderGatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import com.ctc.wstx.shaded.msv_core.util.Uri;

@SpringBootApplication
@EnableEurekaClient
public class GatewayserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayserverApplication.class, args);
	}
	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
	    return builder.routes()
	        .route(p -> p
	            .path("/ark/accounts/**")
	            .filters(f -> f.rewritePath("/ark/accounts/(?<segment>.*)","/${segment}")
	            				.addResponseHeader("X-Response-Time",new Date().toString()))
	            .uri("lb://ACCOUNTS")).
	        route(p -> p
		            .path("/ark/loans/**")
		            .filters(f -> f.rewritePath("/ark/loans/(?<segment>.*)","/${segment}")
		            		.addResponseHeader("X-Response-Time",new Date().toString()))
		            .uri("lb://LOANS")).
	        route(p -> p
		            .path("/ark/cards/**")
		            .filters(f -> f.rewritePath("/ark/cards/(?<segment>.*)","/${segment}")
		            		.addResponseHeader("X-Response-Time",new Date().toString()))
		            .uri("lb://CARDS")).build();
	}
}
