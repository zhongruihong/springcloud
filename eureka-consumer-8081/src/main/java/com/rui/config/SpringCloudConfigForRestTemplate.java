package com.rui.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RetryRule;

@Configuration
public class SpringCloudConfigForRestTemplate {
	// @bean controller @Resource @Autowired
	@Bean
	@LoadBalanced // for ribbon
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	/**
	 * for ribbon 7 kinds of algorithm:
	 * BestAvailableRule AvailabilityFilteringRule WeightedResponseTimeRule
	 * RetryRule RoundRobinRule RandomRule ZoneAvoidanceRule
	 */
	@Bean // for ribbon
	public IRule myRule() {
		return new RetryRule();
	}
}
