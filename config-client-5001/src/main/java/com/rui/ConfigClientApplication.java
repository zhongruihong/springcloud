package com.rui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * start config-server first,then config-client£¡
 * url=http://configserver.rui.com:5001/getPort
 */
@SpringBootApplication
public class ConfigClientApplication {
	public static void main(String[] args) {
		SpringApplication.run(ConfigClientApplication.class, args);
	}
}
