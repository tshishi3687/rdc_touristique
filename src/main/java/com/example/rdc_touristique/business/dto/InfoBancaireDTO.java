package com.example.rdc_touristique.business.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class InfoBancaireDTO implements IdentifiedDTO<Integer>{
    private Integer id;
    private String nomBanque;
    private String numCarte;
    private String numCompte;
    private LocalDate dateExpiration;
    private PersonneSimplifierDTO appartienA;
}
