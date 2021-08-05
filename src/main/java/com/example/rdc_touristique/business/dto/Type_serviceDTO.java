package com.example.rdc_touristique.business.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Type_serviceDTO implements IdentifiedDTO<Integer>{

    private Integer id;
    private String nomtype;
    private LocalDateTime dateCreation;
    private int superid;
}
