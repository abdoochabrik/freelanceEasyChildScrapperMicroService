package com.example.childscrappermicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
public class ChildScrapperMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChildScrapperMicroserviceApplication.class, args);
	}

	@Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
	
}
