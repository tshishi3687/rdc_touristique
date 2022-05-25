package com.example.rdc_touristique.business.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PayPalRDTO {
    private int id;
    private DetailsDTO details;
    private ReservationBienDTO reservationBienDTO;
    private int Prix;
}
