package com.example.childscrappermicroservice.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.example.childscrappermicroservice.domains.offer;
import jakarta.ws.rs.InternalServerErrorException;

@Service()
public class ChildScraperService {
    
     @Autowired private RestTemplate  resteTemplate;
     @Autowired private DiscoveryClient discoveryClient;

      public List<offer> run(String centerOfInterest) {     
        String url = discoveryClient.getInstances("parent-scraper")
                .stream()
                .findFirst()
                .map(serviceInstance -> serviceInstance.getUri().toString())
                .orElse("");     

                if(!url.isEmpty()) {
                   ResponseEntity<List<offer>> responseEntity = resteTemplate.exchange(
			       url + "/parent-scraper-controller/" + centerOfInterest,
                   HttpMethod.GET,
                null,
                   new ParameterizedTypeReference<>() {});

                   return responseEntity.getBody();
                }
                else {
                   throw new InternalServerErrorException("parent scraper instance doesn't exist");
                }
	}
}
