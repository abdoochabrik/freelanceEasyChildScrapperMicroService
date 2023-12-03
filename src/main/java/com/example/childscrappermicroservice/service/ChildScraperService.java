package com.example.childscrappermicroservice.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.example.childscrappermicroservice.domains.offer;
import com.example.childscrappermicroservice.exceptions.ChildScrapperInternalProblem;
import com.example.childscrappermicroservice.exceptions.ParentScrapperInternalProblem;
import com.example.childscrappermicroservice.exceptions.ParentScrapperNotFound;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service()
public class ChildScraperService {

   @Autowired
   private RestTemplate resteTemplate;
   @Autowired
   private DiscoveryClient discoveryClient;
   @Autowired
   private KafkaTemplate<String, String> kafkaTemplate;

   public void run(String centerOfInterest) {
      List<offer> offers;
      String url = discoveryClient.getInstances("parent-scraper")
            .stream()
            .findFirst()
            .map(serviceInstance -> serviceInstance.getUri().toString())
            .orElse("");

      if (!url.isEmpty()) {
         try {
            ResponseEntity<List<offer>> responseEntity = resteTemplate.exchange(
                  url + "/parent-scraper-controller/" + centerOfInterest,
                  HttpMethod.GET,
                  null,
                  new ParameterizedTypeReference<>() {
                  });
            offers = responseEntity.getBody();

            try {
               ObjectMapper objectMapper = new ObjectMapper();
               String jsonString = objectMapper.writeValueAsString(offers);
               kafkaTemplate.send(centerOfInterest, jsonString);

            } catch (Exception e) {
               throw new ChildScrapperInternalProblem("Child scrapper internal problem");
            }

         } catch (Exception e) {
            throw new ParentScrapperInternalProblem("Parent scrapper Internal Problem");
         }
      } else {
         throw new ParentScrapperNotFound("Parent Scrapper not running");
      }
   }
}
