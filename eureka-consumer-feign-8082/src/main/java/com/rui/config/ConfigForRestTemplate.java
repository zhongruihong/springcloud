package com.rui.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RetryRule;

@Configuration
public class ConfigForRestTemplate {
	// @bean controller @Resource @Autowired
	@Bean
	@LoadBalanced // for ribbon
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	/**
	 * for ribbon 7 kinds of algorithm: BestAvailableRule AvailabilityFilteringRule
	 * WeightedResponseTimeRule RetryRule RoundRobinRule RandomRule
	 * ZoneAvoidanceRule
	 */
	@Bean // for ribbon also for feign: because feign has integrateed ribbon and eureka
	public IRule myRule() {
		return new RetryRule();
		// return new BestAvailableRule();
		// return new AvailabilityFilteringRule();
		// return new WeightedResponseTimeRule();
		// return new RoundRobinRule();
		// return new RandomRule();
		// return new ZoneAvoidanceRule();
	}
}
