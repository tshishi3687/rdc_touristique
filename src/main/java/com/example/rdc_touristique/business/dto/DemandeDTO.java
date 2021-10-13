package com.example.rdc_touristique.business.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DemandeDTO implements IdentifiedDTO<Integer>{

    private Integer id;
    private LocalDateTime ddj;
    private LocalDate dda;
    private LocalDate ddd;
    private int npersonne;
    private BienDTO bienDemandee;
    private PersonneSimpleDTO faitPar;
    private EtatDemandeDTO etat;
}
