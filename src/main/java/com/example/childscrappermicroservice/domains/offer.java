package com.example.childscrappermicroservice.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data @Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class offer {
 
    private String link;
    private String title;
    private String time;

}
