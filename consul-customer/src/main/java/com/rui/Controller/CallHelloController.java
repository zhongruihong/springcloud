package com.rui.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CallHelloController {

	@Autowired
	private LoadBalancerClient loader;
	
	@RequestMapping("/call")
	public String call() {
		ServiceInstance serviceInstance = loader.choose("service-producer");
        System.out.println("server address£º" + serviceInstance.getUri());
        System.out.println("server name£º" + serviceInstance.getServiceId());
        //use RestTemplate for rpc
        String callServiceResult = new RestTemplate().getForObject(serviceInstance.getUri().toString() + "/hello", String.class);
        System.out.println(callServiceResult);
        return callServiceResult;
	}
}
