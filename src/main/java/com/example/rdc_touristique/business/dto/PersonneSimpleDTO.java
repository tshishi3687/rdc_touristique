package com.example.rdc_touristique.business.dto;

import lombok.*;

import javax.persistence.Column;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
//    private LocalDateTime dateCreation;
//    private int superid;
}
