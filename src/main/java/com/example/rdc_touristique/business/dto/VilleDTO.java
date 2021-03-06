package com.example.rdc_touristique.business.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class VilleDTO implements IdentifiedDTO<Integer>{

    private Integer id;
    private String nom_ville;
    private Integer nhabitant;
    private ProvinceDTO province;
    private String description;
}
