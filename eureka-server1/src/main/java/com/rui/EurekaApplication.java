package com.rui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
/**
 * url=http://eureka2001.rui.com:2001/
 */
@SpringBootApplication
@EnableEurekaServer // @EnableEurekaServer
public class EurekaApplication {
	public static void main(String[] args) {
		SpringApplication.run(EurekaApplication.class, args);
	}

}
