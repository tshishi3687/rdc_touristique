package com.example.rdc_touristique.business.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BienMisEnLigneDTO implements IdentifiedDTO<Integer> {
    private Integer id;
    private BienVuDTO bienLie;
    private ContratDTO contratBienMisEnLigne;
    private PersonneSimplifierDTO preneurOuBailleur;
}
