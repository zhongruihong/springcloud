package com.rui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class ConsulConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsulConsumerApplication.class, args);
	}
}
