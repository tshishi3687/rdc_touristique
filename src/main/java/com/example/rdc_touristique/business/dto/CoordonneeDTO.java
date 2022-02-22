package com.example.rdc_touristique.business.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CoordonneeDTO implements IdentifiedDTO<Integer>{

    private Integer id;
    private VilleDTO ville;
    private String cpostal;
    private String rue;
    private String num;
    private String email;
    private String telephone;
    private LocalDateTime dateCreation;
}
