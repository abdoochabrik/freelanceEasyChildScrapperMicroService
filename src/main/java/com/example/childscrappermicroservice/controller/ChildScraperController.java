package com.example.childscrappermicroservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.childscrappermicroservice.service.ChildScraperService;

@RestController
@RequestMapping("/child-scraper-controller")
public class ChildScraperController {

  @Autowired
  private ChildScraperService childCraperService;

  @GetMapping("/data")
  public void getDatScraping() throws Exception {
    childCraperService.run("data");
  }

  @GetMapping("/web")
  public void getWebScraping() throws Exception {
    childCraperService.run("web");
  }

  @GetMapping("/security")
  public void getSecurityScraping() throws Exception {
    childCraperService.run("security");
  }

}
