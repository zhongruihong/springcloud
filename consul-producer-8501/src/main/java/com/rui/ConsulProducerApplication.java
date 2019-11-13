package com.rui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
/**
 *pom.xmlÒªÒýÈëribbon! 
 *
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableDiscoveryClient
public class ConsulProducerApplication {
	public static void main(String[] args) {
		SpringApplication.run(ConsulProducerApplication.class, args);
	}
}
