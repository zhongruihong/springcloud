package com.rui.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
/**
 * if there is no ServletRegistrationBean ,Failed opening connection to http://localhost:1001/actuator/hystrix.stream : 404 : HTTP/1.1 404 
 * for dashboard ping
 */
@Configuration
public class ForDashboardConfig {
	@Bean
	public ServletRegistrationBean<HystrixMetricsStreamServlet> getServlet() {
		HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
		ServletRegistrationBean<HystrixMetricsStreamServlet> registrationBean = new ServletRegistrationBean<HystrixMetricsStreamServlet>(
				streamServlet);
		registrationBean.setLoadOnStartup(1);
		registrationBean.addUrlMappings("/actuator/hystrix.stream"); //"/actuator/hystrix.stream", based on DashBoard page information 
		registrationBean.setName("HystrixMetricsStreamServlet");
		return registrationBean;
	}
}