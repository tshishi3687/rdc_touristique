package com.example.rdc_touristique.business.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ServiceDTO implements IdentifiedDTO<Integer>{
    private Integer id;
    private String nom;
    private Type_serviceDTO type;
    private CoordonneeDTO coordonnee;
    private LocalDateTime dateCreation;
}
