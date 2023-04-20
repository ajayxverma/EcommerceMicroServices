package com.airexus.ecommerceconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class EcommerceconfigserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceconfigserverApplication.class, args);
	}

}
