package com.example.rdc_touristique.business.dto;

import lombok.*;


import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DureeOnLineDTO implements IdentifiedDTO<Integer>{
    private Integer id;
    private LocalDate dateDebutOnline;
    private LocalDate dateFinOnline;
}
