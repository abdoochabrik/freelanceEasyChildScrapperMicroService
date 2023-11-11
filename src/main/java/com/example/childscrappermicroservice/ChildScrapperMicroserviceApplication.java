package com.example.childscrappermicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ChildScrapperMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChildScrapperMicroserviceApplication.class, args);
	}

}
