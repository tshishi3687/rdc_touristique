package com.example.rdc_touristique.business.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ValidatorDTO {
    private Boolean ibau;
    private Boolean reservation;
    private Boolean mel;
    private Boolean biensNonMel;
}
