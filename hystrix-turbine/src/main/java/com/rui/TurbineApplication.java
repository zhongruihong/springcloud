package com.rui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.netflix.turbine.EnableTurbine;
/**
 * privider :
 * 1config @Configuration @Bean  ServletRegistrationBean<HystrixMetricsStreamServlet> getServlet() 
 * 2controller's method @HystrixCommand
 *
 *http://localhost:9091/turbine.stream 
 */
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
@EnableTurbine //for turbine
public class TurbineApplication {
	public static void main(String[] args) {
		SpringApplication.run(TurbineApplication.class, args);
	}
}
