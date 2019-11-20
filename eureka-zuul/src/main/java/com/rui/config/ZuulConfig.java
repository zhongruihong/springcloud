package com.rui.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.rui.filter.MyFilter;

@Configuration
public class ZuulConfig {
	@Bean
	public MyFilter accessFilter() {
		return new MyFilter();
	}
}
