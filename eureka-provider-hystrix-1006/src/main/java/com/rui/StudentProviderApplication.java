package com.rui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
/**
 * StudentClientFallbackFactory
 */
@SpringBootApplication
@EnableEurekaClient   //for eureka client
@EnableCircuitBreaker //for hystrix
public class StudentProviderApplication {
	public static void main(String[] args) {
		SpringApplication.run(StudentProviderApplication.class, args);
	}
}
