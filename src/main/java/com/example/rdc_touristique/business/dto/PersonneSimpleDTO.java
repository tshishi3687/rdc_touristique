package com.example.rdc_touristique.business.dto;

import lombok.*;
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
    private RollDTO roll;
    private LocalDateTime ddj;
}
