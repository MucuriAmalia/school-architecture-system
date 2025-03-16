package com.emtech.Api_1_Gateway_Server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Api1GatewayServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(Api1GatewayServerApplication.class, args);
	}

}
