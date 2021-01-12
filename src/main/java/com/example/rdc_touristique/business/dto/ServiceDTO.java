package com.example.rdc_touristique.business.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ServiceDTO implements IdentifiedDTO<Integer>{

    private Integer id;
    private String nom;
    private TypeDTO type;
    private CoordonneeDTO coordonnee;
}
