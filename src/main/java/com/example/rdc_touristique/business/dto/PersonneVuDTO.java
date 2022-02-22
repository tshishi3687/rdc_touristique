package com.example.rdc_touristique.business.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PersonneVuDTO {
    private String nom;
    private String prenom;
    private String role;
    private  boolean active;
}
