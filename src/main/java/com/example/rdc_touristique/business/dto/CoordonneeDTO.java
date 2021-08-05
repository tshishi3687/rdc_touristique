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
    private int cpostal;
    private String rue;
    private int num;
    private String email;
    private int telephone;
    private LocalDateTime dateCreation;
    private int superid;
}
