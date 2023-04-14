package com.airexus.EcommerceApiGateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@EnableHystrix
@SpringBootApplication
public class EcommerceApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApiGatewayApplication.class, args);
	}

}
