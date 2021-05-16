package com.example.rdc_touristique.business.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReservationDTO implements IdentifiedDTO<Integer>{

    private Integer id;
    private LocalDate ddj;
    private LocalDate dda;
    private LocalDate ddd;
    private int npersonne;
    private BienDTO bien_reserve;
    private PersonneSimpleDTO reserverPar;
}
