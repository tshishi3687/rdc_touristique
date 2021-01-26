package com.example.rdc_touristique.business.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PersonneSimpleDTO implements IdentifiedDTO<Integer>{

    private Integer id;
    private String nom;
    private String prenom;
    private LocalDate ddn;
    private String mdp;
    private int telephone;
    private String email;
    private String status;
}
