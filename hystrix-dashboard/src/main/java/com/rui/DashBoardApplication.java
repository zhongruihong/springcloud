package com.rui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
/**
 * privider :
 * 1config @Configuration @Bean  ServletRegistrationBean<HystrixMetricsStreamServlet> getServlet() 
 * 2controller's method @HystrixCommand
 */
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
@EnableHystrixDashboard
public class DashBoardApplication {
	public static void main(String[] args) {
		SpringApplication.run(DashBoardApplication.class, args);
	}
}
