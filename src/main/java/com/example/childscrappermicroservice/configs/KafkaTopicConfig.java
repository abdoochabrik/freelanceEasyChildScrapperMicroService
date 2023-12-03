package com.example.childscrappermicroservice.configs;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration()
public class KafkaTopicConfig {

     @Bean
     public NewTopic webTopic() {
          return TopicBuilder.name("web").build();
     }

     @Bean
     public NewTopic dataTopic() {
          return TopicBuilder.name("data").build();
     }

     @Bean
     public NewTopic securityTopic() {
          return TopicBuilder.name("security").build();
     }

}
