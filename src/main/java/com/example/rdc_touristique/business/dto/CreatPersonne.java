package com.example.rdc_touristique.business.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreatPersonne {
    private Integer id;
    private String nom;
    private String prenom;
    private LocalDate ddn;
    private RollDTO roll;
    private LocalDateTime ddj;
    private ContactUserDTO contactUser;
    private MdpDTO password;
    private String verifMDP;
}
