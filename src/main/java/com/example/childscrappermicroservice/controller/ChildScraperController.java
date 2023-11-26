package com.example.childscrappermicroservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.childscrappermicroservice.domains.offer;
import com.example.childscrappermicroservice.service.ChildScraperService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.ws.rs.InternalServerErrorException;

@RestController
@RequestMapping("/child-scraper-controller")
//@CrossOrigin(origins = "http://localhost:4200/api")
public class ChildScraperController {
    
   @Autowired private ChildScraperService childCraperService;
   @Autowired private KafkaTemplate<String, String> kafkaTemplate;

    @GetMapping("/data")
    public List<offer> getDatScraping() {

      List<offer> result = childCraperService.run("data");
      ObjectMapper objectMapper = new ObjectMapper();
       try {
        String jsonString = objectMapper.writeValueAsString(result);
        kafkaTemplate.send("data", jsonString);
        return result;
    } catch (JsonProcessingException e) {
        throw new InternalServerErrorException("internal error occured");
    }
       
    }

    @GetMapping("/web")
    public /*List<offer>*/ void  getWebScraping() throws Exception {
       System.out.print("web");
       try {
          List<offer> result = childCraperService.run("web");
          ObjectMapper objectMapper = new ObjectMapper();
          String jsonString = objectMapper.writeValueAsString(result);
          kafkaTemplate.send("web", jsonString);
          //return result;
       } catch (Exception e) {
           throw new InternalServerErrorException("internal error occured");
       }
       
    } 


    @GetMapping("/security")
    public List<offer>  getSecurityScraping() throws Exception {
  
      List<offer> result = childCraperService.run("security");
      ObjectMapper objectMapper = new ObjectMapper();
       try {
        String jsonString = objectMapper.writeValueAsString(result);
        kafkaTemplate.send("security", jsonString);
        return result;
    } catch (JsonProcessingException e) {
        throw new InternalServerErrorException("internal error occured");
    }     
    }
}
