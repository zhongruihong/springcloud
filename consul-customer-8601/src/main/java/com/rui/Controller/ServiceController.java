package com.rui.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceController {

	@Autowired
	private LoadBalancerClient loadBalancer;
	@Autowired
	private DiscoveryClient discoveryClient;
	/**
	 * get all servers named "service-producer" and return web page.
	 */
	@RequestMapping("/services")
	public Object serices() {
		return discoveryClient.getInstances("service-producer");
	}
	/**
	 * get a server named "service-producer" randomly [polling]
	 */
	@RequestMapping("/discover")
	public Object discover() {
		return loadBalancer.choose("service-producer").getUri().toString();
	}
}
