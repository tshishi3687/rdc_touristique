package com.example.rdc_touristique.business.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReservationBienDTO {
    private BienDTO bienConserne;
    private PersonneSimplifierDTO faitPar;
    private LocalDate ddArrivee;
    private LocalDate ddDepart;
    private int nPersonneSurLieu;
}
