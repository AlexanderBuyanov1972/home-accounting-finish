package billsservice.config.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PingController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PingController.class);
	
//	@Autowired
//	private DiscoveryClient discoveryClient;
	
//	@GetMapping("/ping")
//	public List<ServiceInstance> ping() {
//		List<ServiceInstance> instances = discoveryClient.getInstances("CLIENT-SERVICE");
//		LOGGER.info("INSTANCES: count={}", instances.size());
//		instances.stream().forEach(it -> LOGGER.info("INSTANCE: id={}, port={}", it.getServiceId(), it.getPort()));
//		return instances;
//	}
}
