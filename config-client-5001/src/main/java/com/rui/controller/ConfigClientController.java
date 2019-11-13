package com.rui.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigClientController {
	/*
	 * If there is no file named crm-test which defined in bootstrap.xml, spring
	 * will look for 'port' in application.xml on path
	 * https://github.com/zhongruihong/springcloud-config-
	 */
	@Value("${port}")
	private String port;
	@Value("${profile}")
	private String profile;

	@GetMapping("/getPort")
	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	@GetMapping("/getProfile")
	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}
}
