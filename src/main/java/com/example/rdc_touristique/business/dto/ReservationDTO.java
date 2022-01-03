package com.example.rdc_touristique.business.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReservationDTO implements IdentifiedDTO<Integer>{

    private Integer id;
    private LocalDateTime dateCreation;
    private BienDTO bienReservee;
    private LocalDate dateArrivee;
    private LocalDate dateDepart;
    private int nPersonneSurLieu;
    private ContratDTO contratLieeReservation;
}
